package com.lm.test.shoppingCart;

import com.lm.test.exception.EmptyShoppingCartException;
import com.lm.test.exception.IncorrectInputFormatException;
import com.lm.test.exception.InvalidPriceException;
import org.junit.Assert;

import com.lm.test.product.Product;
import com.lm.test.product.ProductFactory;
import com.lm.test.receipt.Receipt;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {

    private static String PRODUCT_TEST_DESCRIPTION_1 = "1 box of imported chocolates at 11.25";
    private static String PRODUCT_TEST_DESCRIPTION_2 = "1 imported bottle of perfume at 47.50";
    private static String PRODUCT_TEST_PRICE_1 = "11.25";
    private static String RECEIPT_TEST_TAXES = "7.75";
    private static String RECEIPT_TEST_TOTAL_PRICE = "66.50";
    private Product PRODUCT_TEST_1, PRODUCT_TEST_2 = null;

    @Before
    public void setUp() throws IncorrectInputFormatException {
        PRODUCT_TEST_1 = ProductFactory.createProduct(PRODUCT_TEST_DESCRIPTION_1);
        PRODUCT_TEST_2 = ProductFactory.createProduct(PRODUCT_TEST_DESCRIPTION_2);
    }

    @Test
    public void testAddItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(PRODUCT_TEST_1);

        Assert.assertTrue(shoppingCart.getItems().size() == 1);
        Assert.assertEquals(shoppingCart.getItems().get(0).getPrice().toString(), PRODUCT_TEST_PRICE_1);
    }

    @Test(expected = EmptyShoppingCartException.class)
    public void testGenerateEmptyReceipt() throws InvalidPriceException, EmptyShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        Receipt receipt = shoppingCart.generateReceipt();
    }

    @Test
    public void testGenerateReceipt() throws InvalidPriceException, EmptyShoppingCartException {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(PRODUCT_TEST_1);
        shoppingCart.addItem(PRODUCT_TEST_2);

        Receipt receipt = shoppingCart.generateReceipt();

        Assert.assertNotNull(receipt);
        Assert.assertEquals(receipt.getSalesTaxes().toString(), RECEIPT_TEST_TAXES);
        Assert.assertEquals(receipt.getTotal().toString(), RECEIPT_TEST_TOTAL_PRICE);
    }

}