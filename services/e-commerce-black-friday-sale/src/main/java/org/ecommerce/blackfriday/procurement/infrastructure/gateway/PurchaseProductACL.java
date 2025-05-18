package org.ecommerce.blackfriday.procurement.infrastructure.gateway;

import org.ecommerce.blackfriday.product.managment.application.service.ExistProductService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PurchaseProductACL implements ProductChecker{

    private final ExistProductService existProductService;

    public PurchaseProductACL(ExistProductService existProductService) {
        this.existProductService = existProductService;
    }

    @Override
    public boolean exist(UUID productId) {
        return existProductService.exist(productId);
    }
}
