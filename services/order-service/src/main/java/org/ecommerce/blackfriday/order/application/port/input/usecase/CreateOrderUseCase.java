package org.ecommerce.blackfriday.order.application.port.input.usecase;

import io.smallrye.mutiny.Uni;
import org.ecommerce.blackfriday.order.application.dto.CreateOrderCommand;
import org.ecommerce.blackfriday.order.application.dto.OrderResponse;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;
import org.ecommerce.blackfriday.order.infraestructure.client.dto.Cart;

public interface CreateOrderUseCase {

    Uni<OrderResponse> createOrder (CreateOrderCommand command);

}
