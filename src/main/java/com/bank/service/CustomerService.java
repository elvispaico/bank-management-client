package com.bank.service;

import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerProductResponse;
import com.bank.models.response.CustomerResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface CustomerService {

    Single<Customer> save(CustomerSaveRequest request);

    Single<CustomerResponse> findById(String id);

    Observable<CustomerResponse> findAllCustomers();

    Single<CustomerProductResponse> findCustomerWhitProducts(String idCustomer);

}
