package org.ecommerce.blackfriday.procurement.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.exception.InvalidQuantityDomainException;

public record Quantity (int value) {

    public Quantity {
        int LIMIT_VALUE_QUANTITY = 1;
        if (value < LIMIT_VALUE_QUANTITY) {
            throw new InvalidQuantityDomainException("Invalid Quantity, must be greater to ZERO");
        }
    }

    public int getValue() {
        return value;
    }
}