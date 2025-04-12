package org.ecommerce.blackfriday.product.managment.domain.valueobject;

import java.util.Objects;

public record ProductDescription (String description) {

    public void validateProductName () {
        if (Objects.isNull(this.description) || this.description.isBlank()) {
            throw new IllegalArgumentException("Product Description is required");
        }

        final int CANT_MIN_CHARACTERS = 10;
        if (this.description.length() < CANT_MIN_CHARACTERS) {
            throw new IllegalArgumentException("Product Description is invalid, must have min 5 characters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductDescription that = (ProductDescription) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }
}
