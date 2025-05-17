package org.ecommerce.blackfriday.stock.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class StockId extends BaseId<UUID> {
    public StockId(UUID value) {
        super(value);
    }
}
