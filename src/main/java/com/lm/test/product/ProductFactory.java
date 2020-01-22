package com.lm.test.product;

import com.lm.test.constants.Constants;
import com.lm.test.exception.IncorrectInputFormatException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductFactory {

    private ProductFactory(){};

    public static Product createProduct(String itemLine) throws IncorrectInputFormatException {
        final String itemDescription;
        final List<String> exemptItems = new ArrayList<>(Arrays.asList(Constants.EXEMPT_ITEMS));
        StringBuilder descriptionBuilder = new StringBuilder();
        String descriptionToken;
        BigDecimal price;
        ProductOrigin origin;
        //Default value
        ProductType type = ProductType.REGULAR;
        int quantity = -1;

        Scanner scanner = new Scanner(itemLine);

        if (!scanner.hasNext()) {
            throw new IncorrectInputFormatException();
        }

        if (scanner.hasNextInt()) {
            quantity = scanner.nextInt();
        } else {
            throw new IncorrectInputFormatException();
        }

        while (scanner.hasNext()) {
            descriptionToken = scanner.next();
            if (descriptionToken.equals("at")) {
                break;
            }
            descriptionBuilder.append(descriptionToken);
            descriptionBuilder.append(" ");
        }

        if (descriptionBuilder.length() == 0) {
            throw new IncorrectInputFormatException();
        }

        //Product description without "at" string
        itemDescription = descriptionBuilder.toString().trim();

        if (scanner.hasNextBigDecimal()) {
            price = scanner.nextBigDecimal();
        } else {
            throw new IncorrectInputFormatException();
        }
        scanner.close();

        if (exemptItems.stream().anyMatch(item -> itemDescription.contains(item))) {
            type = ProductType.EXEMPT;
        }

        origin = itemDescription.contains(Constants.IMPORTED_ITEM)? ProductOrigin.IMPORTED : ProductOrigin.LOCAL;

        return new Product(itemDescription, quantity, price, type, origin);
    }
}