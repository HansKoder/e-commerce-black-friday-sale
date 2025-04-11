package org.ecommerce.blackfriday.product.managment.domain.entity;

import org.ecommerce.blackfriday.common.entity.AggregateRoot;
import org.ecommerce.blackfriday.common.valueobject.Money;
import org.ecommerce.blackfriday.common.valueobject.ProductId;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductName;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductStatus;

public class Product extends AggregateRoot<ProductId> {

    private ProductName productName;
    private ProductStatus status;
    private Money price;

    public ProductName getProductName() {
        return productName;
    }

    private void setProductName(ProductName productName) {
        this.productName = productName;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public static final class Builder {
        private ProductName productName;
        private ProductStatus status;
        private Money price;
        private ProductId productId;

        private Builder() {
        }

        public static Builder aProduct() {
            return new Builder();
        }

        public Builder withProductName(ProductName productName) {
            this.productName = productName;
            return this;
        }

        public Builder withStatus(ProductStatus status) {
            this.status = status;
            return this;
        }

        public Builder withPrice(Money price) {
            this.price = price;
            return this;
        }

        public Builder withProductId(ProductId id) {
            this.productId = id;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(productId);
            product.setProductName(this.productName);
            product.setPrice(this.price);
            product.setStatus(this.status);

            return product;
        }
    }

    // methods

    public void validateProduct () {

    }


}
