package org.ecommerce.blackfriday.cart.interfaces.rest.common.dto;

import java.math.BigDecimal;
import java.util.List;

public record GetCartResponse (
        String customerId,
        String cartId,
        List<CartItemResponse> items,
        BigDecimal total
) { }
