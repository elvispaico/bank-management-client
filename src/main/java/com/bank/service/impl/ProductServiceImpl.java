package com.bank.service.impl;

import com.bank.enums.TypeCustomer;
import com.bank.enums.TypeService;
import com.bank.exception.AttributeException;
import com.bank.models.entity.Customer;
import com.bank.models.entity.Product;
import com.bank.repository.CustomerRepository;
import com.bank.repository.ProductRepository;
import com.bank.service.ProductService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void save(Product request) {

        var singleCustomer = Single.fromPublisher(customerRepository.findById(request.getIdCustomer()));

        singleCustomer.subscribe(
                customer -> {
                    if (isCustomerPersonal(customer.getCodTypeCustomer())) {
                        saveCustomerPersonal(customer, request);
                    }
                },
                err -> System.out.println("error")
        );
    }

    /**
     * Metodo para guardar cliente personal
     *
     * @param customer
     * @param request
     */
    private void saveCustomerPersonal(Customer customer, Product request) {
        Observable.fromPublisher(productRepository.findByCustomer(request.getIdCustomer()))
                .filter(product -> product.getCodTypeService().equals(request.getCodTypeService()))
                .isEmpty()
                .subscribe(
                        isDataEmpty -> {
                            if (isDataEmpty && isVerifyCommissionSuccess(request)) {
                                productRepository.save(request).subscribe();
                            } else {
                                throw new AttributeException("cliente ya existe o comision incorrecta");
                            }

                        }
                );

    }

    private boolean isVerifyCommissionSuccess(Product request) {
        if (isTypeServiceWhitCeroCommision(request.getCodTypeService())) {
            return request.getCommission() == 0.0;
        } else {
            return true;
        }
    }

    /**
     * Metodo que verifica si un cliente es personal
     *
     * @param codTypeCustomer parametro tipo String
     * @return valor devuelto tipo Boolean
     */
    private boolean isCustomerPersonal(String codTypeCustomer) {
        return codTypeCustomer.equals(TypeCustomer.PERSONAL.getValue());
    }

    /**
     * Metodo que evalua los tipos de cuenta que no tienen comision
     * por mantenimiento
     *
     * @param codTypeService parametro tipo string
     * @return parametro tipo boolean
     */
    private boolean isTypeServiceWhitCeroCommision(String codTypeService) {
        return codTypeService.equals(TypeService.SAVING.getValue())
                || codTypeService.equals(TypeService.FIXEDTERM.getValue());
    }

}
