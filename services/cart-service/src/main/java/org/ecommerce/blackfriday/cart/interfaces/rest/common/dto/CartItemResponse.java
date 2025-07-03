package org.ecommerce.blackfriday.cart.interfaces.rest.common.dto;

import java.math.BigDecimal;

public record CartItemResponse (
        String cartItemId,
        String productId,
        BigDecimal price,
        int quantity,
        BigDecimal total
) {}
