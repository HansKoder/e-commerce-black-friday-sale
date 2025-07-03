package org.ecommerce.blackfriday.cart.interfaces.rest.quantity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateQuantityRequest(
        @NotNull(message = "CustomerId cannot be null")
        @NotEmpty(message = "CustomerId cannot be empty")
        String customerId,
        @NotNull(message = "Cart Item ID cannot be null")
        @NotEmpty(message = "Cart Item ID must be mandatory")
        String cartItemId,
        @Min(value = 1, message = "The minimum values is 1")
        int quantity
) { }
