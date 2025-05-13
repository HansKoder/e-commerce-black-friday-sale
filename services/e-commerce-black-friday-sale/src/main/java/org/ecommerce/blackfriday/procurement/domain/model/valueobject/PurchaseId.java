package org.ecommerce.blackfriday.procurement.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class PurchaseId extends BaseId<UUID> {
    protected PurchaseId(UUID value) {
        super(value);
    }
}
