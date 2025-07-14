package org.ecommerce.blackfriday.order.infraestructure.persistence.order.mapper;

import io.quarkus.logging.Log;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.order.domain.model.entity.Order;
import org.ecommerce.blackfriday.order.domain.model.entity.OrderItem;
import org.ecommerce.blackfriday.order.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.order.domain.model.valueobject.OrderId;
import org.ecommerce.blackfriday.order.domain.model.valueobject.OrderItemId;
import org.ecommerce.blackfriday.order.domain.model.valueobject.OrderStatus;
import org.ecommerce.blackfriday.order.infraestructure.persistence.order.entity.OrderEntity;
import org.ecommerce.blackfriday.order.infraestructure.persistence.order.entity.OrderItemEntity;
import org.ecommerce.blackfriday.order.infraestructure.persistence.order.entity.OrderStatusJpa;

import java.util.List;
import java.util.UUID;

public class OrderDataAccessMapper {

    public static Order orderFromEntityToDomain (OrderEntity entity) {
        if (entity == null) return null;

        Log.infof("[OrderDataAccessMapper] from Entity to Domain, entity %s", entity);

        Order domain = Order.Builder.anOrder()
                .orderId(new OrderId(entity.id))
                .orderStatus(statusFromEntityToDomain(entity.status))
                .total(new Money(entity.total))
                .customerId(new CustomerId(entity.customerId))
                .items(entity.items.stream()
                        .map(OrderDataAccessMapper::orderItemFromEntityToDomain)
                        .toList())
                .build();

        Log.infof("[OrderDataAccessMapper] from Entity to Domain, mapped %s", domain);

        return domain;
    }

    public static OrderEntity orderFromDomainToEntity (Order domain) {
        if (domain == null) return null;

        Log.infof("[Mapper Order] From Domain To Entity, domain %s", domain);

        OrderEntity entity = new OrderEntity();

        entity.id = domain.getId() != null ? domain.getId().getValue() : UUID.randomUUID();

        entity.total = domain.getTotal().getAmount();
        entity.status = statusFromDomainToEntity(domain.getOrderStatus());
        entity.customerId = domain.getCustomerId().getValue();
        entity.items = getOrderItemsEntities(domain.getItems());

        // Add FK Order
        entity.items.forEach(item -> item.order = entity);

        Log.infof("[Mapper Order] From Domain To Entity, mapped %s", entity);

        return entity;
    }

    private static List<OrderItemEntity> getOrderItemsEntities (List<OrderItem> items) {
        return items.stream()
                .map(OrderDataAccessMapper::orderItemFromDomainToEntity)
                .toList();
    }

    // Order Item Mapper
    private static OrderItem orderItemFromEntityToDomain (OrderItemEntity entity) {
        if (entity == null) return null;

        Log.infof("[Mapper OrderItem] FromEntityToDomain, entity: %s", entity);

        return OrderItem.Builder.anOrderItem()
                // .orderId(new OrderId(entity.order.id))
                .orderItemId(new OrderItemId(entity.id))
                .price(new Money(entity.price))
                .subtotal(new Money(entity.subTotal))
                .quantity(entity.quantity)
                .build();
    }

    private static OrderItemEntity orderItemFromDomainToEntity (OrderItem domain) {
        if (domain == null) return null;

        Log.infof("[Mapper OrderItem] FromDomainToEntity, domain: %s", domain);

        OrderItemEntity entity = new OrderItemEntity();

        entity.id = domain.getId() != null ? domain.getId().getValue() : UUID.randomUUID();

        entity.productId = domain.getProduct().getId().getValue();
        entity.price = domain.getPrice().getAmount();
        entity.subTotal = domain.getSubtotal().getAmount();
        entity.quantity = domain.getQuantity();

        return entity;
    }

    // Status Mapper
    private static OrderStatus statusFromEntityToDomain (OrderStatusJpa entity) {
        if (entity == null) return null;

        return OrderStatus.valueOf(entity.name());
    }

    private static OrderStatusJpa statusFromDomainToEntity (OrderStatus domain) {
        if (domain == null) return null;

        return OrderStatusJpa.valueOf(domain.name());
    }
}
