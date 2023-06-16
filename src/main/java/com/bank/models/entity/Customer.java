package com.bank.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "clients")
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String numDocument;
    private String codTypeDocument;
    private String codTypeCustomer;
    private String address;
    private String email;
    private String phone;

}
