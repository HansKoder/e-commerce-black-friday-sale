package org.ecommerce.blackfriday.cart.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class PriceIsNegativeDomainException extends DomainException {

    public PriceIsNegativeDomainException() {
        super("The price must be positive, this product price is invalid");
    }

}
