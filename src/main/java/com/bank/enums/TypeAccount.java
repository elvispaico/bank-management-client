package com.bank.enums;

public enum TypeAccount {

    SAVING("01"),
    CURRENT("02"),
    FIXEDTERM("03");

    private final String value;

    TypeAccount(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
