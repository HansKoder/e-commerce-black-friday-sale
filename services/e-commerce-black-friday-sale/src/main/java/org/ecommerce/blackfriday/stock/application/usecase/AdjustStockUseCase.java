package org.ecommerce.blackfriday.stock.application.usecase;

import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;
import org.ecommerce.blackfriday.stock.domain.model.enums.StockOperation;
import org.ecommerce.blackfriday.stock.interfaces.rest.stock.dto.request.UpdateStockRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AdjustStockUseCase {

    private final DecreaseStockUseCase decreaseHandler;
    private final IncreaseStockUseCase increaseHandler;

    public AdjustStockUseCase(DecreaseStockUseCase decreaseHandler, IncreaseStockUseCase increaseHandler) {
        this.decreaseHandler = decreaseHandler;
        this.increaseHandler = increaseHandler;
    }

    public Stock handler (UpdateStockRequest request) {
        System.out.println("[ADJUST STOCK USE CASE] request { " + request +  "}");
        List<StockOperation> operationsAllowed = List.of(StockOperation.INCREASE, StockOperation.DECREASE);
        if (!operationsAllowed.contains(request.operation())) throw new UnsupportedOperationException("The operation " + request.operation() + " is not allowed");

        return switch (request.operation()) {
            case INCREASE -> increaseHandler.handler(UUID.fromString(request.productId()), request.value());
            case DECREASE -> decreaseHandler.handler(UUID.fromString(request.productId()), request.value());
        };
    }
}
