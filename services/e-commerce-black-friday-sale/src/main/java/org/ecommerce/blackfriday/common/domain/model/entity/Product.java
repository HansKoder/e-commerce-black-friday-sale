package org.ecommerce.blackfriday.common.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductPrice;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;

import java.util.Objects;

public class Product extends BaseEntity<ProductId> {

    private final String productName;
    private final ProductPrice price;

    private Product(Builder builder) {
        productName = builder.productName;
        price = builder.price;
        super.setId(builder.productId);
    }

    public String getProductName() {
        return productName;
    }

    public ProductPrice getPrice() {
        return price;
    }

    public static class Builder {
        private ProductId productId;
        private String productName;
        private ProductPrice price;

        public Builder withProductId (ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder withProductName (String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withPrice (ProductPrice price) {
            this.price = price;
            return this;
        }

        public Product build () {
            return new Product(this);
        }
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
