package org.ecommerce.blackfriday.order.application.service;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;
import org.ecommerce.blackfriday.order.application.mapper.OrderMapper;
import org.ecommerce.blackfriday.order.application.port.input.usecase.CreateOrderUseCase;
import org.ecommerce.blackfriday.order.application.port.output.client.CartClientPort;
import org.ecommerce.blackfriday.order.application.port.output.repository.OrderRepository;
import org.ecommerce.blackfriday.order.domain.OrderDomainService;

@ApplicationScoped
public class CreateOrderService implements CreateOrderUseCase {

    private final CartClientPort cartClientPort;
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;

    @Inject
    public CreateOrderService(@Named("grpc") CartClientPort cartClientPort, OrderDomainService orderDomainService, OrderRepository orderRepository) {
        this.cartClientPort = cartClientPort;
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Uni<OrderResponse> createOrder(CreateOrderCommand command) {
        Log.infof("[Use Case] create order, payload %s", command);
        return cartClientPort.getCart(command.customerId().toString())
                .map(OrderMapper::toDomain)
                .invoke(orderDomainService::validateAndInitializeOrder)
                .call(orderRepository::save)
                .map(OrderMapper::toResponse);
    }
}
