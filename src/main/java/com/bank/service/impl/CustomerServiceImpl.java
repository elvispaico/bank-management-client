package com.bank.service.impl;

import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerResponse;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Single<Customer> save(CustomerSaveRequest request) {
        return Single.fromPublisher(customerRepository.save(mapRequestToEntity(request)));
    }

    @Override
    public Single<CustomerResponse> findById(String id) {
        return Single.fromPublisher(customerRepository.findById(id))
                .map(this::mapEntityToResponse);
    }

    @Override
    public Observable<CustomerResponse> findAllCustomers() {
        return Observable.fromPublisher(mapEntityToResponse(customerRepository.findAll()));
    }

    private Customer mapRequestToEntity(CustomerSaveRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setNumDocument(request.getNumDocument());
        customer.setCodTypeDocument(request.getCodTypeDocument());
        customer.setCodTypeCustomer(request.getCodTypeCustomer());

        return customer;

    }

    private CustomerResponse mapEntityToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .numDocument(customer.getNumDocument())
                .codTypeDocument(customer.getCodTypeDocument())
                .codTypeCustomer(customer.getCodTypeCustomer())
                .build();
    }

    private Flux<CustomerResponse> mapEntityToResponse(Flux<Customer> listCustomers) {

        return listCustomers.map(this::mapEntityToResponse);


    }


}
