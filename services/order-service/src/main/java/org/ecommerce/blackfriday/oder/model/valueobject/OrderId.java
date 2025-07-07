package org.ecommerce.blackfriday.oder.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class OrderId extends BaseId<UUID> {
    public OrderId(UUID value) {
        super(value);
    }
}
