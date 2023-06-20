package com.bank.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String numDocument;
    private String codTypeDocument;
    private String codTypeCustomer;
    private String address;
    private String email;
    private String phone;
}
