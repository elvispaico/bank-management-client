package com.bank.service;

import com.bank.models.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer save(Customer customer);
}
