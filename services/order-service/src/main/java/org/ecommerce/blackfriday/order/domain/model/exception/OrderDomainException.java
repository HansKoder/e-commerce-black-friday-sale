package org.ecommerce.blackfriday.order.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class OrderDomainException extends DomainException {

    public OrderDomainException(String message) {
        super(message);
    }
}
