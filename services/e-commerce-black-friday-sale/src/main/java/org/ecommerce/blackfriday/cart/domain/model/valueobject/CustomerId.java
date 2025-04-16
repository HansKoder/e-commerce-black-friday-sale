package org.ecommerce.blackfriday.cart.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;

import java.util.UUID;

public class CustomerId extends BaseId<UUID> {

    protected CustomerId(UUID value) {
        super(value);
    }
}
