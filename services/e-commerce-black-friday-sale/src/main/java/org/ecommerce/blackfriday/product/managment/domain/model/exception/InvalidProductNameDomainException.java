package org.ecommerce.blackfriday.product.managment.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class InvalidProductNameDomainException extends DomainException {

    public InvalidProductNameDomainException(String message) {
        super(message);
    }
}
