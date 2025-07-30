package org.hans.fake.payment_gateway_service.card.model.valueobject;

import org.hans.fake.payment_gateway_service.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class CardId extends BaseId<UUID> {
    public CardId(UUID value) {
        super(value);
    }
}
