package org.ecommerce.blackfriday.product.managment.domain.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.AggregateRoot;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.product.managment.domain.exception.ProductDomainException;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductDescription;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductName;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductStatus;

import java.util.Objects;

public class Product extends AggregateRoot<ProductId> {

    private ProductName productName;
    private ProductDescription productDescription;
    private ProductStatus status;
    private Money price;

    public ProductName getProductName() {
        return productName;
    }

    public void setProductName(ProductName productName) {
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

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    // Business Rules
    public void validateInitialProduct() {
        productName.validateProductName();
        productDescription.validateProductName();
        validateInitialStatusProduct();
    }

    private void validateInitialStatusProduct () {
        if (Objects.isNull(status))
            throw new ProductDomainException("Status is not defined");

        if (!status.equals(ProductStatus.DRAFT))
            throw new ProductDomainException("Product Status Initial must be DRAFT");
    }

    public void setInitialPrice (Money money) {
        this.price = money;
    }

}
