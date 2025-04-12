package org.ecommerce.blackfriday.product.managment.infraestructure.persistence.mapper;

import org.ecommerce.blackfriday.common.valueobject.Money;
import org.ecommerce.blackfriday.common.valueobject.ProductId;
import org.ecommerce.blackfriday.product.managment.domain.builder.ProductBuilder;
import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductDescription;
import org.ecommerce.blackfriday.product.managment.domain.valueobject.ProductName;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.entity.ProductEntity;

import java.util.Optional;

public class ProductEntityMapper {

    public static ProductEntity toEntity (Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getProductName().name());
        productEntity.setDescription(product.getProductDescription().description());

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
                .build();
    }

}
