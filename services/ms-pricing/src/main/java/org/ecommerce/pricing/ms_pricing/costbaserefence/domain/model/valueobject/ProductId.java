package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject;

import org.ecommerce.pricing.ms_pricing.shared.domain.model.valueobject.BaseId;

import java.util.UUID;

public class ProductId extends BaseId<UUID> {
    public ProductId(UUID value) {
        super(value);
    }
}
