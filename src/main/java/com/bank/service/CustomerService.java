package com.bank.service;

import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface CustomerService {

    Observable<CustomerResponse> findAllCustomers();

    Single<Customer> save(CustomerSaveRequest request);
}
