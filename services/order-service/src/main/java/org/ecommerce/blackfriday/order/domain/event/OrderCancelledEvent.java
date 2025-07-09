package org.ecommerce.blackfriday.order.domain.event;

import org.ecommerce.blackfriday.order.domain.model.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {

    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
