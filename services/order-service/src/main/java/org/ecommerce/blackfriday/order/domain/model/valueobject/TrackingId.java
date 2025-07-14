package org.ecommerce.blackfriday.order.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {

    public TrackingId(UUID value) {
        super(value);
    }
}
