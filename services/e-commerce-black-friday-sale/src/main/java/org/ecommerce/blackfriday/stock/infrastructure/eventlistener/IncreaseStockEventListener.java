package org.ecommerce.blackfriday.stock.infrastructure.eventlistener;

import org.ecommerce.blackfriday.common.domain.event.IncreaseStockEvent;
import org.ecommerce.blackfriday.stock.application.usecase.IncreaseStockUseCase;
import org.ecommerce.blackfriday.stock.infrastructure.StockLogger;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class IncreaseStockEventListener {
    private final IncreaseStockUseCase useCase;

    public IncreaseStockEventListener(IncreaseStockUseCase useCase) {
        this.useCase = useCase;
    }

    @Async
    @EventListener
    public void handler (IncreaseStockEvent event) {
        StockLogger.info("[LISTENER] (increaseStockEvent) params : event {}", event);
        useCase.handler(event.productId().getValue(), event.quantity());
    }
}
