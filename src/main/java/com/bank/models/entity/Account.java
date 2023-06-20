package com.bank.models.entity;

import com.bank.models.bean.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Clase representa las cuentas bancarias
 */
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "accounts")
public class Account {
    private String id;
    private List<Owner> listOwners;
    private String codTypeAccount;
    private double balance;
    private double commission;
    private int numMovimientos;
    private Date fecMovimiento;
    private boolean status;
}
