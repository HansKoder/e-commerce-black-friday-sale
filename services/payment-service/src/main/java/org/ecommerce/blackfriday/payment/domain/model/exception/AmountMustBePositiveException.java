package org.ecommerce.blackfriday.payment.domain.model.exception;

import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;

public class AmountMustBePositiveException extends DomainException {
    public AmountMustBePositiveException(String message) {
        super(message);
    }
}
