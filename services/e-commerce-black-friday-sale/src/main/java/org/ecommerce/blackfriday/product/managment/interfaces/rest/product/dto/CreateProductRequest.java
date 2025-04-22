package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class CreateProductRequest {

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String productName;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String productDescription;

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
