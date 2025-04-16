package org.ecommerce.blackfriday.cart.domain.model.exception;

import org.ecommerce.blackfriday.common.exception.DomainException;

public class InvalidPriceDomainException extends DomainException {

    public InvalidPriceDomainException(String UUID, String price) {
        super("Product [ID] " + UUID + " has an invalid price, Must be greater to ZERO, [Current Value] " + price);
    }

}
