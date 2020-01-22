package com.lm.test;

import com.lm.test.exception.IncorrectInputFormatException;
import com.lm.test.exception.InvalidPriceException;
import com.lm.test.product.Product;
import com.lm.test.product.ProductFactory;
import com.lm.test.receipt.Receipt;
import com.lm.test.shoppingCart.ShoppingCart;
import org.junit.Test;

public class AppTest {

    private static String INPUT = "INPUT";
    private static String ITEM1_1 = "1 book at 12.49";
    private static String ITEM1_2 = "1 music CD at 14.99";
    private static String ITEM1_3 = "1 chocolate bar at 0.85";
    private static String ITEM2_1 = "1 imported box of chocolates at 10.00";
    private static String ITEM2_2 = "1 imported bottle of perfume at 47.50";
    private static String ITEM3_1 = "1 imported bottle of perfume at 27.99";
    private static String ITEM3_2 = "1 bottle of perfume at 18.99";
    private static String ITEM3_3 = "1 packet of headache pills at 9.75";
    private static String ITEM3_4 = "1 box of imported chocolates at 11.25";
    private static String INVALID_ITEM = "Big box of oranges free";
    private static String INVALID_PRICE_ITEM = "1 box of oranges at 0.0";

    @Test
    public void testInput1() throws Exception {
        System.out.println();
        System.out.println(INPUT);
        System.out.println(ITEM1_1);
        System.out.println(ITEM1_2);
        System.out.println(ITEM1_3);

        Product productTest1 = ProductFactory.createProduct(ITEM1_1);
        Product productTest2 = ProductFactory.createProduct(ITEM1_2);
        Product productTest3 = ProductFactory.createProduct(ITEM1_3);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(productTest1);
        shoppingCart.addItem(productTest2);
        shoppingCart.addItem(productTest3);

        Receipt receipt = shoppingCart.generateReceipt();

        shoppingCart.printReceipt(receipt);
    }


    @Test
    public void testInput2() throws Exception {
        System.out.println();
        System.out.println(INPUT);
        System.out.println(ITEM2_1);
        System.out.println(ITEM2_2);

        Product productTest1 = ProductFactory.createProduct(ITEM2_1);
        Product productTest2 = ProductFactory.createProduct(ITEM2_2);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(productTest1);
        shoppingCart.addItem(productTest2);

        Receipt receipt = shoppingCart.generateReceipt();

        shoppingCart.printReceipt(receipt);
    }

    @Test
    public void testInput3() throws Exception {
        System.out.println();
        System.out.println(INPUT);
        System.out.println(ITEM3_1);
        System.out.println(ITEM3_2);
        System.out.println(ITEM3_3);
        System.out.println(ITEM3_4);

        Product productTest1 = ProductFactory.createProduct(ITEM3_1);
        Product productTest2 = ProductFactory.createProduct(ITEM3_2);
        Product productTest3 = ProductFactory.createProduct(ITEM3_3);
        Product productTest4 = ProductFactory.createProduct(ITEM3_4);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(productTest1);
        shoppingCart.addItem(productTest2);
        shoppingCart.addItem(productTest3);
        shoppingCart.addItem(productTest4);

        Receipt receipt = shoppingCart.generateReceipt();

        shoppingCart.printReceipt(receipt);
    }

    @Test(expected = IncorrectInputFormatException.class)
    public void testInvalidInput() throws InvalidPriceException, IncorrectInputFormatException {
        System.out.println();
        System.out.println(INPUT);
        System.out.println(INVALID_ITEM);

        Product productTest1 = ProductFactory.createProduct(INVALID_ITEM);
    }

    @Test(expected = InvalidPriceException.class)
    public void testInvalidPrice() throws Exception {
        System.out.println();
        System.out.println(INPUT);
        System.out.println(INVALID_PRICE_ITEM);

        Product productTest1 = ProductFactory.createProduct(INVALID_PRICE_ITEM);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(productTest1);

        Receipt receipt = shoppingCart.generateReceipt();

        shoppingCart.printReceipt(receipt);
    }


}