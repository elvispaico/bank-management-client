package com.bank.controller;

import com.bank.models.entity.Product;
import com.bank.models.response.MessageResponse;
import com.bank.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bank/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(
                new MessageResponse(HttpStatus.CREATED.value(), "Product save success"),
                HttpStatus.CREATED);
    }
}
