package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.exception;

public class InvalidStatusProductInitialException extends RuntimeException {

    public InvalidStatusProductInitialException(String message) {
        super(message);
    }

    public InvalidStatusProductInitialException(String message, Throwable cause) {
        super(message, cause);
    }
}
