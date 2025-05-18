package org.ecommerce.blackfriday.stock.application.usecase;

import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;
import org.ecommerce.blackfriday.stock.domain.model.repository.StockRepository;
import org.ecommerce.blackfriday.stock.infrastructure.StockLogger;
import org.ecommerce.blackfriday.stock.infrastructure.gateway.ProductChecker;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.exception.StockInvalidException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IncreaseStockUseCase {
    private final StockRepository stockRepository;
    private final ProductChecker productACL;

    public IncreaseStockUseCase(StockRepository stockRepository, ProductChecker productACL) {
        this.stockRepository = stockRepository;
        this.productACL = productACL;
    }

    public Stock handler (UUID productId, int value) {
        StockLogger.info("[USE CASE] (IncreaseStock) params productId {} quantity {}", productId, value);

        if (!productACL.exist(productId)) {
            String err = "Stock Invalid, the productId " + productId + " does not exist in the DB";
            StockLogger.error("[USE CASE] (IncreaseStock) {}", err);
            throw new StockInvalidException(err);
        }

        Stock domain = stockRepository.findByProductId(productId)
                .orElse(Stock.create(new ProductId(productId)));

        domain.increase(value);
        return stockRepository.save(domain);
    }
}
