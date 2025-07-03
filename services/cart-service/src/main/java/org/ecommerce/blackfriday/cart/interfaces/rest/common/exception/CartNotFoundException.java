package org.ecommerce.blackfriday.cart.interfaces.rest.common.exception;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(String customerId) {
        super("The user's cart with the customer ID " + customerId + " Does not found, you need to check");
    }
}
