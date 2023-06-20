package com.bank.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Document
public class Transaction {
    private String id;
    private String idProduct;
    private LocalDate feTransaction;
    private String codTypeTrasaction;
    private double amount;
}
