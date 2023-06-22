package com.bank.service.impl;

import com.bank.enums.TypeTransaction;
import com.bank.exception.AttributeException;
import com.bank.models.entity.Product;
import com.bank.models.entity.Transaction;
import com.bank.repository.ProductRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.TransactionService;
import com.bank.util.BussinessRules;
import com.bank.util.ProductRule;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    @Override
    public Single<Transaction> save(Transaction transaction) {

        var productSingle = Single.fromPublisher(productRepository.findById(transaction.getIdProduct()));

        var response = productSingle.
                flatMap(product -> {
                    return saveTransaction(product, transaction);
                });

        return response;
    }

    private Single<List<Transaction>> getTransactionByMounth(String idProduct) {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        LocalDate lastDay = today.withDayOfMonth(today.lengthOfMonth());

        var response = Observable.
                fromPublisher(transactionRepository.findByFeTransaction(firstDay, lastDay, idProduct))
                .toList();

        return response;

    }

    private Single<Transaction> saveTransaction(Product product, Transaction transaction) {
        var response = getTransactionByMounth(product.getId())
                .flatMap(lista -> {
                    ProductRule productRule = BussinessRules.getRule(product.getCodTypeService());

                    if (lista.size() < productRule.getNumTrans() && lista.size() < productRule.getLimitMaxTrans()) {

                        updateInMemoryProduct(product, transaction.getCodTypeTransaction(), transaction.getAmount());

                        if (product.getBalance() >= 0.0) {
                            return Single.fromPublisher(productRepository.save(product))
                                    .flatMap(product1 -> {
                                        return Single.fromPublisher(transactionRepository.save(transaction));
                                    });
                        } else {
                            return Single.error(new AttributeException("Monto en transaccion no aplicable"));
                        }


                    } else {
                        return Single.error(new AttributeException("operacion no disponible"));
                    }

                });
        return response;
    }

    @Override
    public Observable<Transaction> findAllByIdProduct(String idProduct) {
        return Observable.fromPublisher(transactionRepository.findAllByIdProduct(idProduct));
    }

    private Product updateInMemoryProduct(Product product, String typeTransaction, double amount) {
        if (typeTransaction.equals(TypeTransaction.DEPOSITO.getValue())) {
            product.setBalance(product.getBalance() + amount);
        } else {
            product.setBalance(product.getBalance() - amount);
        }
        return product;
    }
}
