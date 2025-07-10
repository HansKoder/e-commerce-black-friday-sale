package org.ecommerce.blackfriday.order.application.port.input.service;

import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;
import org.ecommerce.blackfriday.order.infraestructure.client.dto.Cart;

public interface OrderApplicationService {
    Uni<OrderResponse> createOrder (@Valid CreateOrderCommand createOrderCommand);
}
