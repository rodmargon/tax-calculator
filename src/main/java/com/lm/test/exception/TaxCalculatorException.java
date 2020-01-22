package com.lm.test.exception;

public class TaxCalculatorException extends Exception {

    private static final String SEPARATOR = "-#-#-#-#-";

    public TaxCalculatorException(String message) {
        super(SEPARATOR.concat(message).concat(SEPARATOR));
    }
}
