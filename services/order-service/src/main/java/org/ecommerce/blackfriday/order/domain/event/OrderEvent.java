package org.ecommerce.blackfriday.order.domain.event;

import org.ecommerce.blackfriday.common.domain.model.event.DomainEvent;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderEvent implements DomainEvent<Order> {

    private final Order order;
    private final ZonedDateTime createdAt;

    public OrderEvent(Order order,ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        this.order = order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Order getOrder() {
        return order;
    }
}
