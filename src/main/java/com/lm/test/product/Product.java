package com.lm.test.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {

    private final String description;
    private final int quantity;
    private final BigDecimal price;
    private final ProductType type;
    private final ProductOrigin origin;

}
