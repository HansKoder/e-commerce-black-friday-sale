package org.ecommerce.blackfriday.order.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;

    public Money getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    private Product(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.setId(builder.id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static final class Builder {
        private String name;
        private Money price;
        private ProductId id;

        private Builder() {
        }

        public static Builder aProduct() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder id(ProductId id) {
            this.id = id;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
