package com.lm.test.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductType {

    EXEMPT(0),
    REGULAR(10);

    @Getter
    private int taxPercent;

}
