package com.lm.test.exception;

public class InvalidPriceException extends TaxCalculatorException {

    public InvalidPriceException() {
        super("Incorrect product price");
    }
}
