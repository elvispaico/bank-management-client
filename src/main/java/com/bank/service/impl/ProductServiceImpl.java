package com.bank.service.impl;

import com.bank.enums.TypeCustomer;
import com.bank.enums.TypeService;
import com.bank.models.entity.Product;
import com.bank.models.request.ProductSaveRequest;
import com.bank.service.ProductService;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

//    private final ProductRepository productRepository;

    @Override
    public Single<Product> save(ProductSaveRequest request) {

//        var obsProducts = Observable.fromIterable(productRepository.findAllByIdCustomer(request.getIdCustomer()));

        return null;


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
        return codTypeService.equals(TypeService.CURRENT.getValue())
                || codTypeService.equals(TypeService.FIXEDTERM.getValue());
    }

}
