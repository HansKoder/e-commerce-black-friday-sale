package org.ecommerce.blackfriday.order.infraestructure.client.rest.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
    }

    public CartNotFoundException(String message) {
        super(message);
    }
}
