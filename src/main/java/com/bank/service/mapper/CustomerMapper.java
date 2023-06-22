package com.bank.service.mapper;

import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerResponse;
import com.bank.util.ParametrosDataDummy;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerResponse mapCustomerToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .numDocument(customer.getNumDocument())
                .codTypeCustomer(customer.getCodTypeCustomer())
                .desTypeCustomer(customer.getDesTypeCustomer())
                .build();
    }

    public static Customer mapRequestToEntity(CustomerSaveRequest request) {
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

    public static List<CustomerResponse> mapListCustomerToLisCustomerResponse(List<Customer> listCustomers) {
        return listCustomers
                .stream()
                .map(CustomerMapper::mapCustomerToCustomerResponse)
                .collect(Collectors.toList());

    }

}
