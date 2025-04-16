package org.ecommerce.blackfriday.product.managment.domain.valueobject;

import org.ecommerce.blackfriday.product.managment.domain.exception.InvalidProductNameDomainException;

import java.util.Objects;

public record ProductName (String name) {

    public void validateProductName () {
        if (Objects.isNull(this.name) || this.name.isBlank()) {
            throw new InvalidProductNameDomainException("Product Name is required");
        }

        final int CANT_MIN_CHARACTERS = 3;
        if (this.name.length() < CANT_MIN_CHARACTERS) {
            throw new InvalidProductNameDomainException("Product Name is invalid, must have min 5 characters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductName that = (ProductName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
