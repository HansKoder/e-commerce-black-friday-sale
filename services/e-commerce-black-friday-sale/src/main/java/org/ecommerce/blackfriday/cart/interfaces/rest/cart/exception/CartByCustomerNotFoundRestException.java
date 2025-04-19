package org.ecommerce.blackfriday.cart.interfaces.rest.cart.exception;

public class CartByCustomerNotFoundRestException extends RuntimeException {

    public CartByCustomerNotFoundRestException(String customerId) {
        super("The user's cart with the customer ID " + customerId + " Does not found, you need to check");
    }
}
