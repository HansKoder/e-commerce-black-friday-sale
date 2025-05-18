package org.ecommerce.blackfriday.stock.interfaces.rest.stock.exception;

public class StockInvalidException extends RuntimeException{
    public StockInvalidException(String message) {
        super(message);
    }
}
