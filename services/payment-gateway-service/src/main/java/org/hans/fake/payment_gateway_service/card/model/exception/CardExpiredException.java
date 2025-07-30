package org.hans.fake.payment_gateway_service.card.model.exception;

import org.hans.fake.payment_gateway_service.common.domain.model.exception.DomainException;

public class CardExpiredException extends DomainException {

    public CardExpiredException(String message) {
        super(message);
    }
}
