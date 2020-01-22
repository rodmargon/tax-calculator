package com.lm.test.shoppingCart;

import com.lm.test.calculator.Calculator;
import com.lm.test.constants.Constants;
import com.lm.test.exception.EmptyShoppingCartException;
import com.lm.test.exception.InvalidPriceException;
import com.lm.test.product.Product;
import com.lm.test.receipt.Receipt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

public class ShoppingCart {

    @Setter
    @Getter
    private List<Product> items = new ArrayList<Product>();

    public void addItem(Product product){
        items.add(product);
    }

    public void removeItem(Product product){
        items.remove(product);
    }

    public Receipt generateReceipt() throws InvalidPriceException, EmptyShoppingCartException {
        Receipt receipt = new Receipt();
        BigDecimal totalTax = new BigDecimal(0.0);
        BigDecimal totalPrice = new BigDecimal(0.0);

        if (items.size() == 0) {
            throw new EmptyShoppingCartException();
        }

        for (Product item : items) {
            Map<String, BigDecimal> priceAndTax = Calculator.calculateFinalPrice(item);
            BigDecimal itemPrice = priceAndTax.get(Constants.PRICE_CONSTANT);
            totalTax = totalTax.add(priceAndTax.get(Constants.TAX_CONSTANT));
            totalPrice = totalPrice.add(itemPrice);

            Product product = new Product(item.getDescription(), item.getQuantity(), itemPrice, item.getType(),
                item.getOrigin());
            receipt.addItem(product);
        }

        receipt.setSalesTaxes(totalTax);
        receipt.setTotal(totalPrice);
        return receipt;
    }

    public void printReceipt(Receipt receipt){
        System.out.println();
        System.out.println(Constants.OUTPUT);
        receipt.getProducts().forEach(p -> System.out.println(p.getDescription() + ": " + p.getPrice()));
        System.out.println( Constants.SALES_TAXES + receipt.getSalesTaxes());
        System.out.println( Constants.TOTAL + receipt.getTotal());
        System.out.println();
    }

}
