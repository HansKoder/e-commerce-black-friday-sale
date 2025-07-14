package org.ecommerce.blackfriday.order.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.order.domain.model.valueobject.OrderId;
import org.ecommerce.blackfriday.order.domain.model.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subtotal;

    private OrderItem (Builder builder) {
        this.orderId = builder.orderId;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.subtotal = builder.subtotal;

        this.setId(builder.orderItemId);
    }

    boolean isPriceValid () {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(subtotal);
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubtotal() {
        return subtotal;
    }

    public static final class Builder {
        private OrderId orderId;
        private Product product;
        private int quantity;
        private Money price;
        private Money subtotal;
        private OrderItemId orderItemId;

        private Builder() {
        }

        public static Builder anOrderItem() {
            return new Builder();
        }

        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder subtotal(Money subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder orderItemId(OrderItemId id) {
            this.orderItemId = id;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
