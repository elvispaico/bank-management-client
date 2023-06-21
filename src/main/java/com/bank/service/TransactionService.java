package com.bank.service;

import com.bank.models.entity.Transaction;
import io.reactivex.rxjava3.core.Single;

public interface TransactionService {

    Single<Transaction> registerTransactionAccount(Transaction transaction);
}
