package org.ecommerce.blackfriday.product.managment.domain.valueobject;

import java.util.Objects;

public class ProductName {

    private final String name;

    public ProductName(String name) {
        this.name = name;
        this.validateProductName();
    }

    private void validateProductName () {
        if (Objects.isNull(this.name) || this.name.isBlank()) {
            throw new IllegalArgumentException("Product Name is required");
        }

        if (this.name.length() < 5) {
            throw new IllegalArgumentException("Product Name is invalid, must be 5 characters");
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
