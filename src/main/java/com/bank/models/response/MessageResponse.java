package com.bank.models.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private int code;
    private String message;
}
