package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.mapper;

import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.product.managment.domain.model.builder.ProductBuilder;
import org.ecommerce.blackfriday.product.managment.domain.model.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.model.valueobject.ProductDescription;
import org.ecommerce.blackfriday.product.managment.domain.model.valueobject.ProductName;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.builder.GetProductResponseBuilder;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.CreateProductRequest;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.GetProductResponse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class ProductDTOMapper {

    public static GetProductResponse toDto (Product domain) {
        if (Objects.isNull(domain)) return null;
        String status = Objects.isNull(domain.getStatus()) ? "" : domain.getStatus().name();

        return GetProductResponseBuilder.aGetProductResponse()
                .withUuid(domain.getId().getValue().toString())
                .withProductName(domain.getProductName().name())
                .withProductDescription(domain.getProductDescription().description())
                .withPrice(Optional.ofNullable(domain.getPrice()).isPresent() ? domain.getPrice().getAmount() : BigDecimal.ZERO)
                .withStatus(status)
                .build();
    }

    public static Product toCreateProductDomain (CreateProductRequest request) {
        if (Objects.isNull(request)) return null;

        return ProductBuilder.aProduct()
                .withProductId(new ProductId(UUID.randomUUID()))
                .withProductName(new ProductName(request.productName()))
                .withProductDescription(new ProductDescription(request.productDescription()))
                .build();
    }

}
