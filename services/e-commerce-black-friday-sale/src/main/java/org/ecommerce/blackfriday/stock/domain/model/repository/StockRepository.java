package org.ecommerce.blackfriday.stock.domain.model.repository;

import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository {
    Stock save (Stock domain);
    Optional<Stock> findByProductId (UUID productId);
}
