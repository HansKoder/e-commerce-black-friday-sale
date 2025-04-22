package org.ecommerce.blackfriday.product.managment.domain.builder;

import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductDescription;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductName;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductStatus;

public final class ProductBuilder {
    private ProductName productName;
    private ProductDescription productDescription;
    private ProductStatus status;
    private Money price;
    private ProductId productId;

    private ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withProductName(ProductName productName) {
        this.productName = productName;
        return this;
    }

    public ProductBuilder withProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
        return this;
    }

    public ProductBuilder withStatus(ProductStatus status) {
        this.status = status;
        return this;
    }

    public ProductBuilder withPrice(Money price) {
        this.price = price;
        return this;
    }

    public ProductBuilder withProductId(ProductId id) {
        this.productId = id;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setId(productId);
        product.setProductName(this.productName);
        product.setPrice(this.price);
        product.setStatus(this.status);
        product.setProductDescription(this.productDescription);

        return product;
    }
}