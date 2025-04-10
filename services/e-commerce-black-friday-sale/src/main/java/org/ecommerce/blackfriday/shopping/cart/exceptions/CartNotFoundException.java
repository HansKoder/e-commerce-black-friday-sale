package org.ecommerce.blackfriday.shopping.cart.exceptions;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(String cartId) {
        super("Cart with the [ID] " + cartId + " not found.");
    }
}
