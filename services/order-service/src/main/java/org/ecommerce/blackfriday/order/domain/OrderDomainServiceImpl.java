package org.ecommerce.blackfriday.order.domain;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.order.domain.event.OrderCancelledEvent;
import org.ecommerce.blackfriday.order.domain.event.OrderCreatedEvent;
import org.ecommerce.blackfriday.order.domain.event.OrderPaidEvent;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ApplicationScoped
public class OrderDomainServiceImpl implements OrderDomainService{

    private final ZonedDateTime UTC = ZonedDateTime.now(ZoneId.of("UTC"));

    @Override
    public OrderCreatedEvent validateAndInitializeOrder(Order order) {
        Log.infof("[Order Domain Service] Create-order, payload order %s", order);
        order.validateOrder();
        order.initializeOrder();
        Log.infof("Order with id %s is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, UTC);
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        Log.infof("Order with id %s is paid", order.getId().getValue());
        return new OrderPaidEvent(order, UTC);
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        Log.infof("Order with id %s is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order) {
        order.initCancel();
        Log.infof("Order payment is cancelling for order id %s", order.getId().getValue());
        return new OrderCancelledEvent(order, UTC);
    }

    @Override
    public void cancelOrder(Order order) {
        order.cancel();
        Log.infof("Order with id %s is cancelled", order.getId().getValue());
    }
}
