package com.lm.test.calculator;

import com.lm.test.constants.Constants;
import com.lm.test.exception.InvalidPriceException;
import com.lm.test.product.Product;
import com.lm.test.product.ProductOrigin;
import com.lm.test.product.ProductType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CalculatorTest {

    @Test
    public void testRoundNumberUp() {
        BigDecimal roundedNumber = Calculator.roundNumber(new BigDecimal("7.125"));
        Assert.assertEquals(roundedNumber.toString(), "7.15");
    }

    @Test
    @Parameters(method = "createExemptLocalProduct")
    public void testCalculateFinalPriceExemptLocal(Product product) throws InvalidPriceException {
        Map<String, BigDecimal> priceAndTax = new HashMap<String, BigDecimal>();
        priceAndTax = Calculator.calculateFinalPrice(product);

        Assert.assertEquals(priceAndTax.get(Constants.PRICE_CONSTANT).toString(), "11.25");
        Assert.assertEquals(priceAndTax.get(Constants.TAX_CONSTANT).toString(), "0.00");
    }

    @Test
    @Parameters(method = "createExemptImportedProduct")
    public void testCalculateFinalPriceExemptImported(Product product) throws InvalidPriceException {
        Map<String, BigDecimal> priceAndTax = new HashMap<String, BigDecimal>();
        priceAndTax = Calculator.calculateFinalPrice(product);

        Assert.assertEquals(priceAndTax.get(Constants.PRICE_CONSTANT).toString(), "11.85");
        Assert.assertEquals(priceAndTax.get(Constants.TAX_CONSTANT).toString(), "0.60");
    }

    @Test
    @Parameters(method = "createRegularLocalProduct")
    public void testCalculateFinalPriceRegularLocal(Product product) throws InvalidPriceException {
        Map<String, BigDecimal> priceAndTax = new HashMap<String, BigDecimal>();
        priceAndTax = Calculator.calculateFinalPrice(product);

        Assert.assertEquals(priceAndTax.get(Constants.PRICE_CONSTANT).toString(), "12.40");
        Assert.assertEquals(priceAndTax.get(Constants.TAX_CONSTANT).toString(), "1.15");
    }

    @Test
    @Parameters(method = "createRegularImportedProduct")
    public void testCalculateFinalPriceRegularImported(Product product) throws InvalidPriceException {
        Map<String, BigDecimal> priceAndTax = new HashMap<String, BigDecimal>();
        priceAndTax = Calculator.calculateFinalPrice(product);

        Assert.assertEquals(priceAndTax.get(Constants.PRICE_CONSTANT).toString(), "12.95");
        Assert.assertEquals(priceAndTax.get(Constants.TAX_CONSTANT).toString(), "1.70");
    }

    @Test(expected = InvalidPriceException.class)
    @Parameters(method = "createZeroPriceProduct")
    public void testCalculateInvalidPrice(Product product) throws InvalidPriceException {
        Map<String, BigDecimal> priceAndTax = new HashMap<String, BigDecimal>();
        priceAndTax = Calculator.calculateFinalPrice(product);
    }

    @Test(expected = InvalidPriceException.class)
    @Parameters(method = "createNegativePriceProduct")
    public void testCalculateNegativePrice(Product product) throws InvalidPriceException {
        Map<String, BigDecimal> priceAndTax = new HashMap<String, BigDecimal>();
        priceAndTax = Calculator.calculateFinalPrice(product);
    }

    private Object createExemptLocalProduct(){
        return new Object []{ Product.builder().origin(ProductOrigin.LOCAL)
            .quantity(1).price(new BigDecimal("11.25")).type(ProductType.EXEMPT).build()} ;
    }

    private Object createExemptImportedProduct(){
        return new Object []{ Product.builder().origin(ProductOrigin.IMPORTED)
            .quantity(1).price(new BigDecimal("11.25")).type(ProductType.EXEMPT).build()} ;
    }

    private Object createRegularLocalProduct(){
        return new Object []{ Product.builder().origin(ProductOrigin.LOCAL)
            .quantity(1).price(new BigDecimal("11.25")).type(ProductType.REGULAR).build()} ;
    }

    private Object createRegularImportedProduct(){
        return new Object []{ Product.builder().origin(ProductOrigin.IMPORTED)
            .quantity(1).price(new BigDecimal("11.25")).type(ProductType.REGULAR).build()} ;
    }

    private Object createZeroPriceProduct() {
        return new Object[] {Product.builder().price(new BigDecimal("-0")).build()};
    }

    private Object createNegativePriceProduct() {
        return new Object[] {Product.builder().price(new BigDecimal("-1")).build()};
    }
}