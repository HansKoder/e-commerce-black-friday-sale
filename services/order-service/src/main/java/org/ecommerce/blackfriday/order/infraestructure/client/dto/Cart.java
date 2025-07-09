package org.ecommerce.blackfriday.order.infraestructure.client.dto;

import java.math.BigDecimal;
import java.util.List;

public record Cart (
        String customerId,
        String cartId,
        List<CartItem> items,
        BigDecimal total
) { }
