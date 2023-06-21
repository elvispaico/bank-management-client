package com.bank.controller;

import com.bank.models.entity.Product;
import com.bank.service.ProductService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bank/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Single<Product> save(@RequestBody Product product) {
        return productService.save(product);

    }

    @GetMapping("/{id}")
    public Single<Product> fingProductById(@PathVariable String id) {
        return productService.findById(id);
    }

}
