package com.bank.service.impl;

import com.bank.exception.AttributeException;
import com.bank.models.bean.ProductDto;
import com.bank.models.entity.Customer;
import com.bank.models.entity.Product;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerProductResponse;
import com.bank.models.response.CustomerResponse;
import com.bank.repository.CustomerRepository;
import com.bank.repository.ProductRepository;
import com.bank.service.CustomerService;
import com.bank.util.ParametrosDataDummy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public Single<Customer> save(CustomerSaveRequest request) {

        var response = customerRepository.findByNumDocument(request.getNumDocument())
                .hasElement()
                .flatMap(customerExists -> {
                    if (customerExists) {
                        return Mono.error(new AttributeException("Cliente ya existe"));
                    } else {
                        return customerRepository.save(mapRequestToEntity(request));
                    }
                });

        return Single.fromPublisher(response);
    }

    @Override
    public Single<CustomerResponse> findById(String id) {
        return Single.fromPublisher(customerRepository.findById(id))
                .map(this::mapEntityToResponse);
    }

    @Override
    public Observable<CustomerResponse> findAllCustomers() {

        var response =  Observable.fromPublisher(customerRepository.findAll())
                .toList()
                .flatMap(customers -> {
                   return Single.just(mapEntityToResponse(customers));
                });

        return  response
                .toObservable()
                .flatMap(customerResponses -> Observable.fromIterable(customerResponses));

//        return Observable.fromPublisher(mapEntityToResponse(customerRepository.findAll()));
    }

    @Override
    public Single<CustomerProductResponse> findCustomerWhitProducts(String idCustomer) {
        var response = Single.fromPublisher(customerRepository.findById(idCustomer))
                .flatMap(customer -> {
                    return Observable.fromPublisher(productRepository.findByCustomer(customer.getId()))
                            .toList()
                            .flatMap(lisProducts -> {
                                return Single.just(mapCustomerToDtoResponse(customer, mapProducentityToDto(lisProducts)));
                            });
                });
        return response;
    }

    private List<ProductDto> mapProducentityToDto(List<Product> lisProducts) {
        return lisProducts.stream()
                .map(product -> {
                    return ProductDto.builder()
                            .id(product.getId())
                            .codTypeProduct(product.getCodTypeProduct())
                            .codTypeService(product.getCodTypeService())
                            .balance(product.getBalance())
                            .commission(product.getCommission())
                            .build();
                }).collect(Collectors.toList());
    }

    private CustomerProductResponse mapCustomerToDtoResponse(Customer customer, List<ProductDto> lisProducts) {
        return CustomerProductResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .numDocument(customer.getNumDocument())
                .codTypeCustomer(customer.getCodTypeCustomer())
                .products(lisProducts)
                .build();
    }

    private Customer mapRequestToEntity(CustomerSaveRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setNumDocument(request.getNumDocument());
        customer.setCodTypeCustomer(request.getCodTypeCustomer());
        customer.setDesTypeCustomer(ParametrosDataDummy.getData().get("01")
                .stream()
                .filter(param -> param.getCode().equals(request.getCodTypeCustomer()))
                .map(param -> param.getDescription())
                .findFirst()
                .orElse(""));
        return customer;

    }

    private CustomerResponse mapEntityToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .numDocument(customer.getNumDocument())
                .codTypeCustomer(customer.getCodTypeCustomer())
                .desTypeCustomer(customer.getDesTypeCustomer())
                .build();
    }

    private List<CustomerResponse> mapEntityToResponse(List<Customer> listCustomers) {

        return listCustomers
                .stream()
                .map(customer -> mapEntityToResponse(customer))
                .collect(Collectors.toList());

    }


}
