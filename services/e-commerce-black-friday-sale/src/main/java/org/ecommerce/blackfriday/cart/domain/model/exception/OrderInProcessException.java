package org.ecommerce.blackfriday.cart.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class OrderInProcessException extends DomainException {
    public OrderInProcessException(String message) {
        super(message);
    }
}
