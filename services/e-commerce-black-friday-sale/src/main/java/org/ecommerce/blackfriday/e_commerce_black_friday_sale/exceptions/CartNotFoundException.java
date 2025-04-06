package org.ecommerce.blackfriday.e_commerce_black_friday_sale.exceptions;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(String cartId) {
        super("Cart with the [ID] " + cartId + " not found.");
    }
}
