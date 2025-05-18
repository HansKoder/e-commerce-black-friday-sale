package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.exception;

public class ProductNotFoundRestException extends RuntimeException{
    public ProductNotFoundRestException(String productId) {
        super("The product ID " + productId + " does not exist in the DB" );
    }
}
