package org.ecommerce.blackfriday.order.application.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateOrderCommand (
        @NotNull(message = "CartId is mandatory")
        UUID cartId,
        @NotNull(message = "CustomerId is mandatory")
        UUID customerId
) {
        @Override
        public String toString() {
                return "CreateOrderCommand{" +
                        "cartId=" + cartId +
                        ", customerId=" + customerId +
                        '}';
        }
}
