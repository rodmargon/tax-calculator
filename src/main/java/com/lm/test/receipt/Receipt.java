package com.lm.test.receipt;

import com.lm.test.product.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Receipt {

    private List<Product> products = new ArrayList<Product>();
    private BigDecimal total;
    private BigDecimal salesTaxes;

    public void addItem(Product product) {
        products.add(product);
    }

}
