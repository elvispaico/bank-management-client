package com.bank.controller;

import com.bank.models.entity.Transaction;
import com.bank.service.TransactionService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bank/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Single<Transaction> save(@RequestBody Transaction request) {
        return transactionService.save(request);
    }

    @GetMapping(value = "/product/{idProduct}")
    public Observable<Transaction> findAllTransactionsByProduct(@PathVariable String idProduct) {
        return transactionService.findAllByIdProduct(idProduct);
    }
}
