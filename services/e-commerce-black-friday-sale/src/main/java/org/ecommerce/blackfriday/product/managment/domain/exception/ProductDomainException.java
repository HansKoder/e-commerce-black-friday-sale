package org.ecommerce.blackfriday.product.managment.domain.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class ProductDomainException extends DomainException {
    public ProductDomainException(String message) {
        super(message);
    }

    public ProductDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
