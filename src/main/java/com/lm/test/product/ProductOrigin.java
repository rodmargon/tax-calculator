package com.lm.test.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductOrigin {

    LOCAL(0),
    IMPORTED(5);

    @Getter
    private int taxPercent;

}
