package com.bank.service;

import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerResponse;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface CustomerService {

    Single<Customer> save(CustomerSaveRequest request);

    Single<Customer> update(CustomerSaveRequest request);

    Maybe<CustomerResponse> findById(String id);

    Observable<CustomerResponse> findAllCustomers();

}
