package org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.valueobject;

import org.ecommerce.pricing.ms_pricing.shared.domain.model.valueobject.BaseId;

import java.util.UUID;

public class PurchaseRecordId extends BaseId<UUID> {
    public PurchaseRecordId(UUID value) {
        super(value);
    }
}
