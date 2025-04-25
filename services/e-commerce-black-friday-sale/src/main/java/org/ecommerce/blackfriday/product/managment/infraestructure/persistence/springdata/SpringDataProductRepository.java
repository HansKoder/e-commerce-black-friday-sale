package org.ecommerce.blackfriday.product.managment.infraestructure.persistence.springdata;

import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SpringDataProductRepository extends
        JpaRepository<ProductEntity, UUID>,
        JpaSpecificationExecutor<ProductEntity> {
}
