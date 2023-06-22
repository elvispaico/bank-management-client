package com.bank.service;

import com.bank.models.entity.Transaction;
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
}
