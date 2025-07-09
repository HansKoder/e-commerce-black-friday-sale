package org.ecommerce.blackfriday.order.application.service;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;
import org.ecommerce.blackfriday.order.application.mapper.OrderMapper;
import org.ecommerce.blackfriday.order.application.port.input.usecase.CreateOrderUseCase;
import org.ecommerce.blackfriday.order.application.port.output.CartServicePort;
import org.ecommerce.blackfriday.order.domain.OrderDomainService;

@ApplicationScoped
public class CreateOrderService implements CreateOrderUseCase {

    private final CartServicePort cartServicePort;
    private final OrderDomainService orderDomainService;

    @Inject
    public CreateOrderService(CartServicePort cartServicePort, OrderDomainService orderDomainService) {
        this.cartServicePort = cartServicePort;
        this.orderDomainService = orderDomainService;
    }

    @Override
    public Uni<OrderResponse> createOrder(CreateOrderCommand command) {
        Log.infof("[Use Case] create order, payload %s", command);
        return cartServicePort.getCart(command.customerId().toString())
                .map(OrderMapper::toDomain)
                .invoke(orderDomainService::validateAndInitializeOrder)
                .map(OrderMapper::toResponse);
    }
}
