package com.bank.service;

import com.bank.models.entity.Transaction;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface TransactionService {

    /**
     * Metodo que guarda un transaccion para un determinado
     * producto
     *
     * @param transaction
     * @return
     */
    Single<Transaction> save(Transaction transaction);

    /**
     * Metodo para buscar todas las transaciones de un producto
     * por su Id.
     * @param idProduct
     * @return
     */
    Observable<Transaction> findAllByIdProduct(String idProduct);
}
