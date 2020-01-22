package com.lm.test.exception;

public class EmptyShoppingCartException extends TaxCalculatorException {

    public EmptyShoppingCartException() {
        super("Shopping is empty - not allowed to print receipt ");
    }
}
