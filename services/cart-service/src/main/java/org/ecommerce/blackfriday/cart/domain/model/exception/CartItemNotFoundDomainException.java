package org.ecommerce.blackfriday.cart.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class CartItemNotFoundDomainException extends DomainException {
    public CartItemNotFoundDomainException(String message) {
        super(message);
    }
}
