package com.bank.exception;

import com.bank.models.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<MessageResponse> throwAttributeException(AttributeException e) {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
