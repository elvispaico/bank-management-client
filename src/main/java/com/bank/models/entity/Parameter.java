package com.bank.models.entity;

import com.bank.models.bean.ParameterDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Clase para representa los parametros que se van utilizar
 * en el aplicacion.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "parameters")
public class Parameter {
    private String id;
    private List<ParameterDetail> listDetails;
}
