package org.ecommerce.blackfriday.stock.application.usecase;

import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;
import org.ecommerce.blackfriday.stock.domain.model.repository.StockRepository;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.exception.StockNotFoundRestException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DecreaseStockUseCase {
    private final StockRepository stockRepository;

    public DecreaseStockUseCase(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock handler (UUID productId, int value) {
        Stock domain = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundRestException(productId.toString()));

        domain.decrease(value);

        return stockRepository.save(domain);
    }
}
