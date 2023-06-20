package com.bank.service.impl;

import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerResponse;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Single<Customer> save(CustomerSaveRequest request) {
        return Single.create(emitter -> {
            var optionalCustomer = customerRepository.findByNumDocument(request.getNumDocument());
            if (optionalCustomer.isPresent()) {
                emitter.onError(new RuntimeException("ya existe valor"));
            } else {
                var respose = customerRepository.save(mapRequestToEntity(request));
                emitter.onSuccess(respose);
            }
        });

    }

    @Override
    public Single<List<CustomerResponse>> findAllCustomers() {
        return Single.create(emmiter -> {
            var response = customerRepository.findAll();
            emmiter.onSuccess(mapEntityToResponse(response));
        });

    }

    private Customer mapRequestToEntity(CustomerSaveRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setNumDocument(request.getNumDocument());
        customer.setCodTypeDocument(request.getCodTypeDocument());
        customer.setCodTypeCustomer(request.getCodTypeCustomer());
        customer.setEmail(request.getEmail());
        customer.setAddress(Optional.ofNullable(request.getAddress()).orElse(""));
        customer.setPhone(Optional.ofNullable(request.getPhone()).orElse(""));

        return customer;

    }

    private List<CustomerResponse> mapEntityToResponse(List<Customer> listCustomers) {

        return listCustomers.stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .numDocument(customer.getNumDocument())
                        .codTypeDocument(customer.getCodTypeDocument())
                        .codTypeCustomer(customer.getCodTypeCustomer())
                        .address(customer.getAddress())
                        .email(customer.getEmail())
                        .phone(customer.getPhone())
                        .build()).collect(Collectors.toList());

    }


}
