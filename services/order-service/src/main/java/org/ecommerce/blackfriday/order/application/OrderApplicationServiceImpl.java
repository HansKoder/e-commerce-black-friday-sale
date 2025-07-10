package org.ecommerce.blackfriday.order.application;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;
import org.ecommerce.blackfriday.order.application.port.input.service.OrderApplicationService;
import org.ecommerce.blackfriday.order.application.port.input.usecase.CreateOrderUseCase;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;
import org.ecommerce.blackfriday.order.infraestructure.client.dto.Cart;

@ApplicationScoped
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private final CreateOrderUseCase createOrderUseCase;

    @Inject
    public OrderApplicationServiceImpl(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @Override
    public Uni<OrderResponse> createOrder(CreateOrderCommand createOrderCommand) {
        Log.infof("[Order Application Service] payload: %s.", createOrderCommand);
        return this.createOrderUseCase.createOrder(createOrderCommand);
    }
}
