package org.ecommerce.blackfriday.stock.infrastructure.gateway;

import java.util.UUID;

public interface ProductChecker {
    boolean exist(UUID productId);
}
