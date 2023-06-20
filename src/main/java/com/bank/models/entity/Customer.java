package com.bank.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase representa los clientes del banco
 */
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "clients")
public class Customer {
    @Id
    private String id;
    private String name;
    private String numDocument;
    private String codTypeDocument;
    private String codTypeCustomer;
}
