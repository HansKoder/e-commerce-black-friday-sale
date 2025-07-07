package org.ecommerce.blackfriday.oder.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class OrderItemId extends BaseId<UUID> {
    public OrderItemId(UUID value) {
        super(value);
    }
}
