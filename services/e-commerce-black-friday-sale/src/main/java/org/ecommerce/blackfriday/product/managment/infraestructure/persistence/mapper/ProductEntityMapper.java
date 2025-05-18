package org.ecommerce.blackfriday.product.managment.infraestructure.persistence.mapper;

import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.product.managment.domain.model.builder.ProductBuilder;
import org.ecommerce.blackfriday.product.managment.domain.model.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.model.valueobject.ProductDescription;
import org.ecommerce.blackfriday.product.managment.domain.model.valueobject.ProductName;
import org.ecommerce.blackfriday.product.managment.domain.model.valueobject.ProductStatus;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.entity.ProductEntity;

import java.util.Optional;

public class ProductEntityMapper {

    public static ProductEntity toEntity (Product product) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(product.getId().getValue());
        productEntity.setName(product.getProductName().name());
        productEntity.setDescription(product.getProductDescription().description());
        productEntity.setStatus(ProductStatusMapper.toJpa(product.getStatus()));

        if (Optional.ofNullable(product.getPrice()).isPresent())
            productEntity.setPrice(product.getPrice().getAmount());

        return productEntity;
    }

    public static Product toDomain (ProductEntity productEntity) {
        return ProductBuilder.aProduct()
                .withProductId(new ProductId(productEntity.getId()))
                .withProductName(new ProductName(productEntity.getName()))
                .withProductDescription(new ProductDescription(productEntity.getDescription()))
                .withPrice(new Money(productEntity.getPrice()))
                .withStatus(ProductStatusMapper.toDomain(productEntity.getStatus()))
                .build();
    }

}
