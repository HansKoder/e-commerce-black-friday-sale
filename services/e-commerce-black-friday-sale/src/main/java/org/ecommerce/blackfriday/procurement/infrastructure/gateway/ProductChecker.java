package org.ecommerce.blackfriday.procurement.infrastructure.gateway;

import java.util.UUID;

public interface ProductChecker {
    public boolean exist(UUID productId);
}
