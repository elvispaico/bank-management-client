package com.bank.service;

import com.bank.models.entity.Product;
import io.reactivex.rxjava3.core.Single;

public interface ProductService {

    void save(Product request);
}
