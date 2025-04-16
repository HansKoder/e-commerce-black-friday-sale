package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model;

import java.math.BigDecimal;
import java.util.List;

public record RedisCartModel (
    List<RedisCartItemModel> items,
    BigDecimal total
) { }
