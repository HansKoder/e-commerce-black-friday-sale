package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject;

import org.ecommerce.pricing.ms_pricing.shared.domain.model.valueobject.BaseId;

import java.util.UUID;

public class CostReferenceId extends BaseId<UUID> {
    public CostReferenceId(UUID value) {
        super(value);
    }
}
