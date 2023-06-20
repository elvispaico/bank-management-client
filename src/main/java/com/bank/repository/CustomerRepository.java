package com.bank.repository;

import com.bank.models.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {

    Flux<Customer> findByNumDocument(String numDocument);

}
