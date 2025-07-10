package org.ecommerce.blackfriday.order.application.port.output.repository;

import io.smallrye.mutiny.Uni;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;

public interface OrderRepository {
    Uni<Order> save(Order domain);
}
