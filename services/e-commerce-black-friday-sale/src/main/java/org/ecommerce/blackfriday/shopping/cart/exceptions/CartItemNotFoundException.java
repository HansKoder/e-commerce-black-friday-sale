package org.ecommerce.blackfriday.shopping.cart.exceptions;

public class CartItemNotFoundException extends RuntimeException {

    public CartItemNotFoundException(String cartId, String itemId) {
        super("Cart with the [ID] " + cartId + " does not have this item added with the [ID] " + itemId);
    }
}
