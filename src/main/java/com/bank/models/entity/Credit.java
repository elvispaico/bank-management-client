package com.bank.models.entity;

import com.bank.models.bean.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Clase representa los creditos
 */
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "credits")
public class Credit {
    private String id;
    private String codTypeCredit;
    private List<Owner> listOwners;
    private double balance;
    private boolean status;
}
