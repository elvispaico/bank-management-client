package com.bank.controller;

import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerResponse;
import com.bank.models.response.CustomerSaveResponse;
import com.bank.service.CustomerService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bank/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Single<ResponseEntity<CustomerSaveResponse>> save(@RequestBody CustomerSaveRequest request) {
        return customerService.save(request)
                .subscribeOn(Schedulers.io())
                .map(value -> new ResponseEntity<>(value, HttpStatus.CREATED));

    }

    @GetMapping
    public Single<ResponseEntity<List<CustomerResponse>>> findAll() {
        return customerService.findAllCustomers()
                .subscribeOn(Schedulers.io())
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }
}
