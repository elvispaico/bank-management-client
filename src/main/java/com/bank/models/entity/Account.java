package com.bank.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "accounts")
public class Account {
    private String id;
    private String idCustomer;
    private String typeAccount;
    private double balance;
    private double commission;
}
