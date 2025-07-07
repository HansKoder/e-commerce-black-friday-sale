package org.ecommerce.blackfriday.cart.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class CartItemId extends BaseId<UUID> {
    public CartItemId(UUID value) {
        super(value);
    }
}
