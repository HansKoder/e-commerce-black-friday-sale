package org.ecommerce.blackfriday.order.domain.event;

import org.ecommerce.blackfriday.order.domain.model.entity.Order;
import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
