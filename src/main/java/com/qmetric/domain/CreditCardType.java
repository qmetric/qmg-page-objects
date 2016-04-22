package com.qmetric.domain;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 31/07/2013
 */
public enum CreditCardType {

    UNSPECIFIED("Unspecified"), VISA("Visa"), MASTERCARD("Mastercard"), MAESTRO("Maestro");

    private final String value;

    CreditCardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
