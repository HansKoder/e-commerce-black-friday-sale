package org.ecommerce.blackfriday.cart.exceptions;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(String cartId) {
        super("Cart with the [ID] " + cartId + " not found.");
    }
}
