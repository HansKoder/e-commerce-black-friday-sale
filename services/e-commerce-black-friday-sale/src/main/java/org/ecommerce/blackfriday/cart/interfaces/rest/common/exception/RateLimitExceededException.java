package org.ecommerce.blackfriday.cart.interfaces.rest.common.exception;

public class RateLimitExceededException extends RuntimeException{

    public RateLimitExceededException(String message) {
        super(message);
    }
}
