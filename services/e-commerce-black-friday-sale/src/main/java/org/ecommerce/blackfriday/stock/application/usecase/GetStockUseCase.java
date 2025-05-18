package org.ecommerce.blackfriday.stock.application.usecase;

import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;
import org.ecommerce.blackfriday.stock.domain.model.repository.StockRepository;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.exception.StockNotFoundRestException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetStockUseCase {

    private final StockRepository stockRepository;

    public GetStockUseCase(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock handler (String productId) {
        return stockRepository.findByProductId(UUID.fromString(productId))
                .orElseThrow(() -> new StockNotFoundRestException(productId));
    }

}
