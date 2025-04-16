package org.ecommerce.blackfriday.cart.domain.model.entity;

import org.ecommerce.blackfriday.cart.domain.model.exception.InvalidPriceDomainException;
import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;

import java.util.Objects;

public class Product extends BaseEntity<ProductId> {

    private final String productName;
    private final Money price;

    private Product(Builder builder) {
        productName = builder.productName;
        price = builder.price;
    }

    public String getProductName() {
        return productName;
    }

    public Money getPrice() {
        return price;
    }

    public static class Builder {

        private String productName;
        private Money price;

        public Builder withProductName (String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withPrice (Money price) {
            this.price = price;
            return this;
        }

        public Product build () {
            return new Product(this);
        }
    }

    private void priceIsNegative () {
        if (!price.isGreaterThanZero())
            throw new InvalidPriceDomainException(getId().getValue().toString(), price.getAmount().toString());
    }

    public void validatePrice () {
        priceIsNegative();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
