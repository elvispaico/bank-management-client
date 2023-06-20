package com.bank.service;

import com.bank.models.entity.Product;
import com.bank.models.request.ProductSaveRequest;
import io.reactivex.rxjava3.core.Single;

public interface ProductService {

    Single<Product> save(ProductSaveRequest request);
}
