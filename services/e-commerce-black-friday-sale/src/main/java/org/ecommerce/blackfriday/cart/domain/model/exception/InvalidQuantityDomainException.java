package org.ecommerce.blackfriday.cart.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class InvalidQuantityDomainException extends DomainException {
    public InvalidQuantityDomainException(String message) {
        super(message);
    }
}
