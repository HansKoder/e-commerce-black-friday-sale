package org.ecommerce.blackfriday.procurement.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class PurchaseItemId extends BaseId<UUID> {
    protected PurchaseItemId(UUID value) {
        super(value);
    }
}
