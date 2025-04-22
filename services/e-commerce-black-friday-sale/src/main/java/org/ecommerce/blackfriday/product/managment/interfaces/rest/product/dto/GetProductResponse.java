package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto;

import java.math.BigDecimal;

public class GetProductResponse {

    private String uuid;
    private String productName;
    private String productDescription;
    private BigDecimal price;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
