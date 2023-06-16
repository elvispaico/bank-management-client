package com.bank.service;

import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerResponse;
import com.bank.models.response.CustomerSaveResponse;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface CustomerService {

    Single<List<CustomerResponse>> findAllCustomers();

    Single<CustomerSaveResponse> save(CustomerSaveRequest request);
}
