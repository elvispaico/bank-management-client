package com.bank.service;

import com.bank.models.entity.Customer;
import com.bank.models.request.CustomerSaveRequest;
import com.bank.models.response.CustomerProductResponse;
import com.bank.models.response.CustomerResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface CustomerService {
    /**
     * Metodo para guardar un cliente
     *
     * @param request Objeto para guardar solo los campos necesarios
     * @return
     */
    Single<Customer> save(CustomerSaveRequest request);

    /**
     * Metodo que busca un cliente por el ID, que representa la
     * clave primaria
     *
     * @param id
     * @return
     */
    Single<CustomerResponse> findById(String id);


    /**
     * Metodo que retorna todos los clientes registrado
     *
     * @return
     */
    Observable<CustomerResponse> findAllCustomers();

    /**
     * Metodos que busca un cliente porel ID, en la consulta
     * tambien incluye un lista de todos los productos que tiene
     * el cliente
     *
     * @param idCustomer
     * @return
     */
    Single<CustomerProductResponse> findCustomerWhitProducts(String idCustomer);

}
