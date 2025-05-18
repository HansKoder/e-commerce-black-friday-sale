package org.ecommerce.blackfriday.stock.application.usecase;

import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;
import org.ecommerce.blackfriday.stock.domain.model.repository.StockRepository;
import org.ecommerce.blackfriday.stock.infrastructure.StockLogger;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IncreaseStockUseCase {
    private final StockRepository stockRepository;

    public IncreaseStockUseCase(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock handler (UUID productId, int value) {
        StockLogger.info("[USE CASE] (IncreaseStock) params productId {} quantity {}", productId, value);
        Stock domain = stockRepository.findByProductId(productId)
                .orElse(Stock.create(new ProductId(productId)));

        domain.increase(value);

        return stockRepository.save(domain);
    }
}
