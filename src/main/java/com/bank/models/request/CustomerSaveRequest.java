package com.bank.models.request;

import com.bank.models.bean.ProfileCustomer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerSaveRequest {
    private String name;
    private String numDocument;
    private String codTypeCustomer;
    private String desTypeCustomer;
    private ProfileCustomer profile;
}
