package com.bank.controller;

import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerResponse;
import com.bank.service.CustomerService;
import com.bank.models.response.MessageResponse;
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
    public Single<ResponseEntity<MessageResponse>> save(@RequestBody CustomerSaveRequest request) {
        return customerService.save(request)
                .subscribeOn(Schedulers.io())
                .map(value -> new ResponseEntity<>(
                        new MessageResponse(HttpStatus.CREATED.value(),"Customer save success"),
                        HttpStatus.CREATED)
                );

    }

    @GetMapping
    public Single<ResponseEntity<List<CustomerResponse>>> findAll() {
        return customerService.findAllCustomers()
                .subscribeOn(Schedulers.io())
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }
}
