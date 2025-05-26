package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.exception;

import org.ecommerce.pricing.ms_pricing.shared.domain.model.exception.DomainException;

public class MissingPurchasePriceStrategy extends DomainException {
    public MissingPurchasePriceStrategy(String message) {
        super(message);
    }
}
