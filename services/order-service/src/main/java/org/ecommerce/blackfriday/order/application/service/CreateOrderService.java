package org.ecommerce.blackfriday.order.application.service;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;
import org.ecommerce.blackfriday.order.application.mapper.OrderMapper;
import org.ecommerce.blackfriday.order.application.port.input.usecase.CreateOrderUseCase;
import org.ecommerce.blackfriday.order.application.port.output.client.CartServicePort;
import org.ecommerce.blackfriday.order.application.port.output.repository.OrderRepository;
import org.ecommerce.blackfriday.order.domain.OrderDomainService;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;
import org.ecommerce.blackfriday.order.infraestructure.client.dto.Cart;

@ApplicationScoped
public class CreateOrderService implements CreateOrderUseCase {

    private final CartServicePort cartServicePort;
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;

    @Inject
    public CreateOrderService(CartServicePort cartServicePort, OrderDomainService orderDomainService, OrderRepository orderRepository) {
        this.cartServicePort = cartServicePort;
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Uni<OrderResponse> createOrder(CreateOrderCommand command) {
        Log.infof("[Use Case] create order, payload %s", command);
        return cartServicePort.getCart(command.customerId().toString())
                .map(OrderMapper::toDomain)
                .invoke(orderDomainService::validateAndInitializeOrder)
                .call(orderRepository::save)
                .map(OrderMapper::toResponse);
    }
}
