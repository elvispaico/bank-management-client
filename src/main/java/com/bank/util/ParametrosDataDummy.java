package com.bank.util;

import com.bank.models.bean.ParameterDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametrosDataDummy {

    private static final Map<String, List<ParameterDetail>> data = new HashMap<>();

    static {
        data.put("01", List.of(
           new ParameterDetail("01","Personal"),
           new ParameterDetail("02","Empresarial")
        ));
        data.put("02", List.of(
                new ParameterDetail("01", "CB-Cuenta Ahorro"),
                new ParameterDetail("02", "CB-Cuenta Corriente"),
                new ParameterDetail("03", "CB-Cuenta Plazo Fijo"),
                new ParameterDetail("04", "CR-Personal"),
                new ParameterDetail("05", "CR-Empresarial"),
                new ParameterDetail("06", "CR-Tarjeta Credito"))
        );
    }

    public static Map<String, List<ParameterDetail>> getData() {
        return data;
    }
}
