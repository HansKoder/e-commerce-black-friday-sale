package org.ecommerce.blackfriday.cart.interfaces.rest.common.exception;

public class TimeoutException extends RuntimeException{

    public TimeoutException(String message) {
        super(message);
    }
}
