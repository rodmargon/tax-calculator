package com.lm.test.calculator;

import com.lm.test.constants.Constants;
import com.lm.test.exception.InvalidPriceException;
import com.lm.test.product.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private Calculator() {};

    public static Map<String, BigDecimal> calculateFinalPrice(Product product) throws InvalidPriceException {

        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPriceException();
        }

        Map<String, BigDecimal> priceAndTax = new HashMap<String, BigDecimal>();
        int totalTaxPercent = product.getType().getTaxPercent() + product.getOrigin().getTaxPercent();
        BigDecimal totalAmountItem = product.getPrice().multiply(new BigDecimal(product.getQuantity()));
        BigDecimal tax = (totalAmountItem).multiply(new BigDecimal(totalTaxPercent)).divide(new BigDecimal(100));
        tax = roundNumber(tax);

        BigDecimal productPrice = tax.add(totalAmountItem);
        priceAndTax.put(Constants.TAX_CONSTANT, tax);
        priceAndTax.put(Constants.PRICE_CONSTANT, productPrice);

        return priceAndTax;
    }

    /**
     * @return number rounded up to nearest 0.05
     */
    public static BigDecimal roundNumber(BigDecimal number) {
        BigDecimal result = new BigDecimal(Math.ceil(number.doubleValue() * 20) / 20);
        result = result.setScale(Constants.DECIMAL_DIGITS, BigDecimal.ROUND_HALF_UP);
        return result;
    }
}
