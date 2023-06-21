package com.bank.repository;

import com.bank.models.entity.Transaction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, String> {

    @Query("{ 'feTransaction': { $gte: ?0, $lte: ?1 }, 'idProduct': ?2  }")
    Flux<Transaction> findByFeTransaction(LocalDate firsDate, LocalDate lastDate, String idProduct);
}
