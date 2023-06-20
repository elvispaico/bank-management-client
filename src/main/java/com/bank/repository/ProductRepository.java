package com.bank.repository;

import com.bank.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

    Iterable<Product> findAllByIdCustomer(String idCustomers);
}
