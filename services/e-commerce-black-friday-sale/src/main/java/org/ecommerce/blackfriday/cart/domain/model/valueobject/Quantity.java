package org.ecommerce.blackfriday.cart.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.exception.InvalidQuantityDomainException;

import java.util.Objects;

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

    public Quantity decrement () {
        if (this.value == 1)
            throw new InvalidQuantityDomainException("Invalid operation, It cannot decrement below ONE (1)");

        return new Quantity(this.value - 1);
    }

    public Quantity increment () {
        return new Quantity(this.value + 1);
    }

    public Quantity set(int newValue) {
        if (newValue < 1)
            throw new InvalidQuantityDomainException("Invalid operation, Quantity must be great to ZERO");

        return new Quantity(newValue);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return value == quantity.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
