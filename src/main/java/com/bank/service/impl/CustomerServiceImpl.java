package com.bank.service.impl;

import com.bank.exception.AttributeException;
import com.bank.exception.ResourceNotFoundException;
import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerProductResponse;
import com.bank.models.response.CustomerResponse;
import com.bank.repository.CustomerRepository;
import com.bank.repository.ProductRepository;
import com.bank.service.CustomerService;
import com.bank.service.mapper.CustomerMapper;
import com.bank.service.mapper.ProductMapper;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Single<Customer> save(CustomerSaveRequest request) {

        var response = customerRepository.findByNumDocument(request.getNumDocument())
                .hasElement()
                .flatMap(customerExists -> {
                    if (customerExists) {
                        return Mono.error(new AttributeException("Cliente ya existe"));
                    } else {
                        return customerRepository.save(CustomerMapper.mapRequestToEntity(request));
                    }
                });

        return Single.fromPublisher(response);
    }

    @Override
    public Single<CustomerResponse> findById(String id) {
        return Single.fromPublisher(customerRepository.findById(id))
                .map(CustomerMapper::mapCustomerToCustomerResponse)
                .onErrorResumeNext(error -> Single.error(new ResourceNotFoundException("cliente no encontrado")));
    }

    @Override
    public Observable<CustomerResponse> findAllCustomers() {

        var response = Observable.fromPublisher(customerRepository.findAll())
                .toList()
                .flatMap(customers -> {
                    return Single.just(CustomerMapper.mapListCustomerToLisCustomerResponse(customers));
                });

        return response
                .toObservable()
                .flatMap(customerResponses -> Observable.fromIterable(customerResponses));

    }

    @Override
    public Single<CustomerProductResponse> findCustomerWhitProducts(String idCustomer) {
        var response = Single.fromPublisher(customerRepository.findById(idCustomer))
                .flatMap(customer -> {
                    return Observable.fromPublisher(productRepository.findByCustomer(customer.getId()))
                            .toList()
                            .flatMap(lisProducts -> {
                                CustomerProductResponse ctResponse = CustomerProductResponse.builder()
                                        .id(customer.getId())
                                        .name(customer.getName())
                                        .numDocument(customer.getNumDocument())
                                        .codTypeCustomer(customer.getCodTypeCustomer())
                                        .products(ProductMapper.mapListProductToListProductDto(lisProducts))
                                        .build();
                                return Single.just(ctResponse);
                            });
                });
        return response;
    }

}
