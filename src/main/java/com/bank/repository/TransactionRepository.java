package com.bank.repository;

import com.bank.models.entity.Transaction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, String> {
    /**
     * Metodo que busca las transaciones o movimientos que ha realizado
     * un cliente desde un intervalo de fechas
     *
     * @param firsDate  Fecha Inicio
     * @param lastDate  Fecha Fin
     * @param idProduct Id Producto
     * @return
     */
    @Query("{ 'feTransaction': { $gte: ?0, $lte: ?1 }, 'idProduct': ?2  }")
    Flux<Transaction> findByFeTransaction(LocalDate firsDate, LocalDate lastDate, String idProduct);

    Flux<Transaction> findAllByIdProduct(String idProduct);
}
