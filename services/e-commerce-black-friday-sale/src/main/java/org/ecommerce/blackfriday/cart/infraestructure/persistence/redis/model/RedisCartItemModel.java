package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model;

import java.math.BigDecimal;

public record RedisCartItemModel (
        String productId,
        BigDecimal productPrice,
        int quantity,
        BigDecimal total
) { }
