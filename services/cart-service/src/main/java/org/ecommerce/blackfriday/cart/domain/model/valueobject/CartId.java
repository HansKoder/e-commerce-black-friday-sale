package org.ecommerce.blackfriday.cart.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class CartId extends BaseId<UUID> {
    public CartId(UUID value) {
        super(value);
    }
}
