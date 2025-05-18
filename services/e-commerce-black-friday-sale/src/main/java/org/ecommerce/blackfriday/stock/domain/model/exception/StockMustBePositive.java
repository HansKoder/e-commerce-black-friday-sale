package org.ecommerce.blackfriday.stock.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class StockMustBePositive extends DomainException {

    public StockMustBePositive(String productId) {
        super("The stock must be positive, It could not be update the stock's product with the ID " + productId);
    }
}
