package org.ecommerce.blackfriday.cart.interfaces.rest.item.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public final class DeleteCartItemRequest {

    @NotNull(message = "CustomerId cannot be null")
    @NotEmpty(message = "CustomerId cannot be empty")
    private final String customerId;

    @NotNull(message = "Cart Item ID cannot be null")
    @NotEmpty(message = "Cart Item ID must be mandatory")
    private final String cartItemId;

    public DeleteCartItemRequest(String customerId, String cartItemId) {
        this.customerId = customerId;
        this.cartItemId = cartItemId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCartItemId() {
        return cartItemId;
    }
}
