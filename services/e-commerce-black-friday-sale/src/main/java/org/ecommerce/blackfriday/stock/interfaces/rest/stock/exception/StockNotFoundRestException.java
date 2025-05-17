package org.ecommerce.blackfriday.stock.interfaces.rest.stock.exception;

public class StockNotFoundRestException extends RuntimeException {
    public StockNotFoundRestException(String productId) {
        super("The product with the ID [" + productId + "] does not find in the DB");
    }
}
