package com.lm.test.exception;

public class IncorrectInputFormatException extends TaxCalculatorException {

    public IncorrectInputFormatException() {
        super("Incorrect input format");
    }
}
