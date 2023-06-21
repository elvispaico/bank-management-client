package com.bank.models.bean;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class ProductDto {
    private String id;
    private String codTypeProduct;
    private String desTypeProduct;
    private String codTypeService;
    private String desTypeService;
    private double balance;
    private double commission;
}
