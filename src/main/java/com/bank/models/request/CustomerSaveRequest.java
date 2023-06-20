package com.bank.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerSaveRequest {
    private String name;
    private String numDocument;
    private String codTypeDocument;
    private String codTypeCustomer;
    private String address;
    private String email;
    private String phone;
}
