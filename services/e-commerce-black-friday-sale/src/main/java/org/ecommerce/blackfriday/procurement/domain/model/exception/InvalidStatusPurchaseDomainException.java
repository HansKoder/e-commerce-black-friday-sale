package org.ecommerce.blackfriday.procurement.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class InvalidStatusPurchaseDomainException extends DomainException {
    public InvalidStatusPurchaseDomainException(String message) {
        super(message);
    }
}
