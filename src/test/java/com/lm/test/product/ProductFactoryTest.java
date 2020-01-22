package com.lm.test.product;

import com.lm.test.exception.IncorrectInputFormatException;
import java.math.BigDecimal;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;

public class ProductFactoryTest {

    private static final String IMPORTED_EXEMPT_PRODUCT = "1 imported chocolate bar at 27.99";
    private static final String IMPORTED_REGULAR_PRODUCT = "1 imported bottle of perfume at 27.99";
    private static final String LOCAL_EXEMPT_PRODUCT = "1 chocolate bar at 27.99";
    private static final String LOCAL_REGULAR_PRODUCT = "1 pair of shoes at 27.99";
    private static final String FORMATTED_IMP_EXEM_PRODUCT_DESCRIPTION = "imported chocolate bar";
    private static final String FORMATTED_IMP_REG_PRODUCT_DESCRIPTION = "imported bottle of perfume";
    private static final String FORMATTED_LOCAL_EXEMP_PRODUCT_DESCRIPTION = "chocolate bar";
    private static final String FORMATTED_LOCAL_REG_PRODUCT_DESCRIPTION = "pair of shoes";
    private static final BigDecimal PRICE = new BigDecimal("27.99");
    private static final int TAX_PERCENT_IMPORTED = 5;
    private static final int TAX_PERCENT_EXEMPT = 0;
    private static final int TAX_PERCENT_REGULAR = 10;
    private static final String PRODUCT_NO_NUMBER = "Imported bottle of perfume at 27.99";
    private static final String PRODUCT_NO_PRICE = "1 Imported bottle of perfume";
    private static final String PRODUCT_INVALID_FORMAT = "Imported 1 bottle of perfume 14.33";
    private static final String EMPTY_PRODUCT_DESCRIPTION = "1 14.33";

    @Test
    public void testCreateImportedExemptProduct() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct(IMPORTED_EXEMPT_PRODUCT);
        Assert.assertNotNull(productTest);
        Assert.assertEquals(productTest.getDescription(), FORMATTED_IMP_EXEM_PRODUCT_DESCRIPTION);
        Assert.assertEquals(productTest.getQuantity(), 1);
        Assert.assertEquals(productTest.getPrice(), PRICE);
        Assert.assertEquals(productTest.getOrigin().getTaxPercent(), TAX_PERCENT_IMPORTED);
        Assert.assertEquals(productTest.getType().getTaxPercent(), TAX_PERCENT_EXEMPT);
        Assert.assertEquals(productTest.getType().toString(), ProductType.EXEMPT.toString());
    }

    @Test
    public void testCreateImportedRegularProduct() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct(IMPORTED_REGULAR_PRODUCT);
        Assert.assertNotNull(productTest);
        Assert.assertEquals(productTest.getDescription(), FORMATTED_IMP_REG_PRODUCT_DESCRIPTION);
        Assert.assertEquals(productTest.getQuantity(), 1);
        Assert.assertEquals(productTest.getPrice(), PRICE);
        Assert.assertEquals(productTest.getOrigin().getTaxPercent(), TAX_PERCENT_IMPORTED);
        Assert.assertEquals(productTest.getType().getTaxPercent(), TAX_PERCENT_REGULAR);
        Assert.assertEquals(productTest.getType().toString(), ProductType.REGULAR.toString());
    }

    @Test
    public void testCreateLocalExemptProduct() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct(LOCAL_EXEMPT_PRODUCT);
        Assert.assertNotNull(productTest);
        Assert.assertEquals(productTest.getDescription(), FORMATTED_LOCAL_EXEMP_PRODUCT_DESCRIPTION);
        Assert.assertEquals(productTest.getQuantity(), 1);
        Assert.assertEquals(productTest.getPrice(), PRICE);
        Assert.assertEquals(productTest.getOrigin().getTaxPercent(), TAX_PERCENT_EXEMPT);
        Assert.assertEquals(productTest.getType().getTaxPercent(), TAX_PERCENT_EXEMPT);
        Assert.assertEquals(productTest.getType().toString(), ProductType.EXEMPT.toString());
    }

    @Test
    public void testCreateLocalRegularProduct() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct(LOCAL_REGULAR_PRODUCT);
        Assert.assertNotNull(productTest);
        Assert.assertEquals(productTest.getDescription(), FORMATTED_LOCAL_REG_PRODUCT_DESCRIPTION);
        Assert.assertEquals(productTest.getQuantity(), 1);
        Assert.assertEquals(productTest.getPrice(), PRICE);
        Assert.assertEquals(productTest.getOrigin().getTaxPercent(), TAX_PERCENT_EXEMPT);
        Assert.assertEquals(productTest.getType().getTaxPercent(), TAX_PERCENT_REGULAR);
        Assert.assertEquals(productTest.getType().toString(), ProductType.REGULAR.toString());
    }

    @Test(expected = IncorrectInputFormatException.class)
    public void testEmptyProduct() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct("");
    }

    @Test(expected = IncorrectInputFormatException.class)
    public void testNoNumberOfProductDescription() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct(PRODUCT_NO_NUMBER);
    }

    @Test(expected = IncorrectInputFormatException.class)
    public void testNoPriceOfProductDescription() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct(PRODUCT_NO_PRICE);
    }

    @Test(expected = IncorrectInputFormatException.class)
    public void testIncorrectFormatProductDescription() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct(PRODUCT_INVALID_FORMAT);
    }

    @Test(expected = IncorrectInputFormatException.class)
    public void testEmptyProductDescription() throws IncorrectInputFormatException {
        Product productTest = ProductFactory.createProduct(EMPTY_PRODUCT_DESCRIPTION);
    }

}