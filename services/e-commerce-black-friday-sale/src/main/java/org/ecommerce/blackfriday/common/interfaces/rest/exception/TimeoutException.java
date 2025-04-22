package org.ecommerce.blackfriday.common.interfaces.rest.exception;

public class TimeoutException extends RuntimeException {

    public TimeoutException(String message) {
        super(message);
    }
}
