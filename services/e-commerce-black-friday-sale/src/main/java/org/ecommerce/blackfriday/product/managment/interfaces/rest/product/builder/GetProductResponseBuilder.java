package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.builder;

import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.GetProductResponse;

import java.math.BigDecimal;

public final class GetProductResponseBuilder {
    private String uuid;
    private String productName;
    private String productDescription;
    private BigDecimal price;

    private GetProductResponseBuilder() {
    }

    public static GetProductResponseBuilder aGetProductResponse() {
        return new GetProductResponseBuilder();
    }

    public GetProductResponseBuilder withUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public GetProductResponseBuilder withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public GetProductResponseBuilder withProductDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }

    public GetProductResponseBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public GetProductResponse build() {
        GetProductResponse getProductResponse = new GetProductResponse();
        getProductResponse.setUuid(uuid);
        getProductResponse.setProductName(productName);
        getProductResponse.setProductDescription(productDescription);
        getProductResponse.setPrice(price);
        return getProductResponse;
    }
}
