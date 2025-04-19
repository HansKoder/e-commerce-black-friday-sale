package org.ecommerce.blackfriday.cart.interfaces.rest.quantity.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DecrementQuantityRequest(
        @NotNull(message = "CustomerId cannot be null")
        @NotEmpty(message = "CustomerId cannot be empty")
        String customerId,
        @NotNull(message = "Cart Item ID cannot be null")
        @NotEmpty(message = "Cart Item ID must be mandatory")
        String cartItemId
) { }
