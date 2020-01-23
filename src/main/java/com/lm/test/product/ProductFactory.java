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
        BigDecimal price;
        ProductOrigin origin;
        //Default value
        ProductType type = ProductType.REGULAR;
        int quantity = -1;

        Scanner scanner = new Scanner(itemLine);

        if (!scanner.hasNext()) {
            throw new IncorrectInputFormatException();
        }

        quantity = getQuantity(scanner);
        
        //Product description without "at" string
        itemDescription = getDescription(scanner);

        price = getPrice(scanner);
        
        scanner.close();

        if (exemptItems.stream().anyMatch(item -> itemDescription.contains(item))) {
            type = ProductType.EXEMPT;
        }

        origin = itemDescription.contains(Constants.IMPORTED_ITEM)? ProductOrigin.IMPORTED : ProductOrigin.LOCAL;

        return new Product(itemDescription, quantity, price, type, origin);
    }
    
    private static int getQuantity(Scanner scanner) throws IncorrectInputFormatException {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            throw new IncorrectInputFormatException();
        }
    }

    private static String getDescription(Scanner scanner) throws IncorrectInputFormatException {
        StringBuilder descriptionBuilder = new StringBuilder();
        String descriptionToken;
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
        return descriptionBuilder.toString().trim();
    }

    private static BigDecimal getPrice(Scanner scanner) throws IncorrectInputFormatException{
         if (scanner.hasNextBigDecimal()) {
           return scanner.nextBigDecimal();
        } else {
            throw new IncorrectInputFormatException();
        }
    }
}
