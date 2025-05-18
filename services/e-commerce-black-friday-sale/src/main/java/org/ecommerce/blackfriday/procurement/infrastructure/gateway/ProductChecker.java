package org.ecommerce.blackfriday.procurement.infrastructure.gateway;

import java.util.UUID;

public interface ProductChecker {
    boolean exist(UUID productId);
}
