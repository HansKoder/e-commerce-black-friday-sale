package org.ecommerce.blackfriday.order.domain;

import org.ecommerce.blackfriday.order.domain.event.OrderCancelledEvent;
import org.ecommerce.blackfriday.order.domain.event.OrderCreatedEvent;
import org.ecommerce.blackfriday.order.domain.event.OrderPaidEvent;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitializeOrder (Order order);
    OrderPaidEvent payOrder (Order order);
    void approveOrder (Order order);
    OrderCancelledEvent cancelOrderPayment (Order order);
    void cancelOrder (Order order);

}
