package com.bank.repository;

import com.bank.models.entity.Product;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {

    @Query("{'idCustomer' :  ?0 }")
    Flux<Product> findByCustomer(String idCustomer);

}
