package org.ecommerce.blackfriday.order.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.AggregateRoot;
import org.ecommerce.blackfriday.common.domain.model.exception.DomainException;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.order.domain.model.exception.OrderDomainException;
import org.ecommerce.blackfriday.order.domain.model.valueobject.*;

import java.util.List;
import java.util.UUID;

public class Order extends AggregateRoot<OrderId> {
    private final List<OrderItem> items;
    private final CustomerId customerId;
    private final Money total;
    private final StreetAddress deliveryAddress;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> errors;

    public void initializeOrder () {
        this.setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
    }

    public void validateOrder () {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice ();
    }

    public void pay () {
        if (orderStatus != OrderStatus.PENDING)
            throw new OrderDomainException("Order is not in correct state for pay operation!");

        orderStatus = OrderStatus.PAID;
    }

    public void approve () {
        if (orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in correct state for approve operation!");

        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel () {
        if (orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in correct state for init cancelling operation!");

        orderStatus = OrderStatus.CANCELLING;
    }

    public void cancel () {
        boolean couldCancel = orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING;
        if (!couldCancel)
            throw new OrderDomainException("Order is not in correct state for cancel operation!");

        orderStatus = OrderStatus.CANCELLED;
    }

    private void validateItemsPrice() {
        Money orderItemsTotal = items.stream()
                .map(orderItem -> {
                    validateItemPrice(orderItem);
                    return orderItem.getSubtotal();
                })
                .reduce(Money.ZERO, Money::add);

        if (!orderItemsTotal.equals(total))
            throw new OrderDomainException("Total Price: " + total.getAmount() +
            " is not equal to Order Items Total: " + orderItemsTotal.getAmount());
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order Item Price: " + orderItem.getPrice().getAmount()
            + " is not valid for product " + orderItem.getProduct().getId().getValue());
        }
    }

    private void validateTotalPrice () {
        if (total == null || !total.isGreaterThanZero() )
            throw new OrderDomainException("Total must be greater than zero");
    }

    private void validateInitialOrder () {
        if (orderStatus != null || getId() != null)
            throw new DomainException("The order is not in correct state for initialization");
    }

    private Order (Builder builder) {
        this.setId(builder.orderId);
        this.items = builder.items;
        this.customerId = builder.customerId;
        this.total = builder.total;
        this.deliveryAddress = builder.deliveryAddress;

        this.trackingId = builder.trackingId;
        this.orderStatus = builder.orderStatus;
        this.errors = builder.errors;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Money getTotal() {
        return total;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public static final class Builder {
        private CustomerId customerId;
        private List<OrderItem> items;
        private Money total;
        private StreetAddress deliveryAddress;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> errors;
        private OrderId orderId;

        private Builder() {
        }

        public static Builder anOrder() {
            return new Builder();
        }

        public Builder customerId(CustomerId customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder items(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Builder total(Money total) {
            this.total = total;
            return this;
        }

        public Builder deliveryAddress(StreetAddress deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Builder trackingId(TrackingId trackingId) {
            this.trackingId = trackingId;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder errors(List<String> errors) {
            this.errors = errors;
            return this;
        }

        public Builder orderId(OrderId id) {
            this.orderId = id;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId=" + customerId +
                ", items=" + items +
                ", total=" + total +
                ", deliveryAddress=" + deliveryAddress +
                ", trackingId=" + trackingId +
                ", orderStatus=" + orderStatus +
                ", errors=" + errors +
                '}';
    }
}
