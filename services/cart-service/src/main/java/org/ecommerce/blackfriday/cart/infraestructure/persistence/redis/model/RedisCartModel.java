package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model;

import java.math.BigDecimal;
import java.util.List;

public record RedisCartModel (
        String cartId,
        List<RedisCartItemModel> items,
        BigDecimal total
) { }
