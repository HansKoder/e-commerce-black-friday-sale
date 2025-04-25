package org.ecommerce.blackfriday.product.managment.interfaces.rest.product.mapper;

import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.product.managment.domain.builder.ProductBuilder;
import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductDescription;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductName;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.builder.GetProductResponseBuilder;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.CreateProductRequest;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.GetProductResponse;

import java.util.Optional;
import java.util.UUID;

public class ProductDTOMapper {

    public static GetProductResponse toDto (Product domain) {
        return GetProductResponseBuilder.aGetProductResponse()
                .withUuid(domain.getId().getValue().toString())
                .withProductName(domain.getProductName().name())
                .withProductDescription(domain.getProductDescription().description())
                .withPrice(Optional.ofNullable(domain.getPrice()).isPresent() ? domain.getPrice().getAmount() : null)
                .build();
    }

    public static Product toCreateProductDomain (CreateProductRequest request) {
        return ProductBuilder.aProduct()
                .withProductId(new ProductId(UUID.randomUUID()))
                .withProductName(new ProductName(request.productName()))
                .withProductDescription(new ProductDescription(request.productDescription()))
                .build();
    }

}
