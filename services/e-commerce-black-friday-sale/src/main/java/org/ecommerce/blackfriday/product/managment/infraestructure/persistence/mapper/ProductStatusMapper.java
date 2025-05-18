package org.ecommerce.blackfriday.product.managment.infraestructure.persistence.mapper;

import org.ecommerce.blackfriday.product.managment.domain.model.valueobject.ProductStatus;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.entity.ProductStatusJPA;

import java.util.Objects;

public class ProductStatusMapper {

    public static ProductStatus toDomain (ProductStatusJPA jpa) {
        if (Objects.isNull(jpa)) return null;

        return ProductStatus.valueOf(jpa.name());
    }

    public static ProductStatusJPA toJpa (ProductStatus domain) {
        if (Objects.isNull(domain)) return null;

        return ProductStatusJPA.valueOf(domain.name());
    }

}
