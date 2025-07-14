package org.ecommerce.blackfriday.order.application.port.input.usecase;

import io.smallrye.mutiny.Uni;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;

public interface CreateOrderUseCase {

    Uni<OrderResponse> createOrder (CreateOrderCommand command);

}
