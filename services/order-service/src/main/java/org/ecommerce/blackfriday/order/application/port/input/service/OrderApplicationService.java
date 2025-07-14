package org.ecommerce.blackfriday.order.application.port.input.service;

import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;

public interface OrderApplicationService {
    Uni<OrderResponse> createOrder (@Valid CreateOrderCommand createOrderCommand);
}
