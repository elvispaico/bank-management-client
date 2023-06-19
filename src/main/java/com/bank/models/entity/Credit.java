package com.bank.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "credits")
public class Credit {
    private String id;
    private String idCustomer;
    private double balance;
}
