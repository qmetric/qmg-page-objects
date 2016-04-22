package com.qmetric.pageobjects.enquiry_forms.legacy;

import com.qmetric.domain.CreditCardType;

public class PaymentDetails {
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String securityCode;
    private CreditCardType creditCardType = CreditCardType.UNSPECIFIED;

    public PaymentDetails() {
        this.cardNumber = "4107858958607710";
        this.expiryMonth = "07";
        this.expiryYear = "2018";
        this.securityCode = "111";
    }

    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryMonth(final String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public void setExpiryYear(final String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public void setSecurityCode(final String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    @Override
    public String toString() {
        return "PaymentDetails [cardNumber=" + cardNumber + ", expiryMonth=" + expiryMonth +
                ", expiryYear=" + expiryYear + ", securityCode=" + securityCode +
                ", creditCardType=" + creditCardType.getValue() + "]";
    }

}
