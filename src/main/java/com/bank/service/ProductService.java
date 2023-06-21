package com.bank.service;

import com.bank.models.entity.Product;
import io.reactivex.rxjava3.core.Single;

public interface ProductService {

    Single<Product> save(Product request);

    Single<Product> findById(String id);
}
