package com.bank.controller;

import com.bank.models.entity.Transaction;
import com.bank.service.TransactionService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bank/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Single<Transaction> save(@RequestBody Transaction request) {
        return transactionService.registerTransactionAccount(request);
    }

}
