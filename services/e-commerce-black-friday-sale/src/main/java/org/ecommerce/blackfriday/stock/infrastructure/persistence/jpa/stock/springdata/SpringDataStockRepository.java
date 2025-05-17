package org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.springdata;

import org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.entity.StockEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataStockRepository extends CrudRepository<StockEntity, UUID> {
    Optional<StockEntity> findByProductId(UUID productId);
}
