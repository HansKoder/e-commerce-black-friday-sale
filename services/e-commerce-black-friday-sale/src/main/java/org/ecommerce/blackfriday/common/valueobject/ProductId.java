package org.ecommerce.blackfriday.common.valueobject;

import java.util.UUID;

public class ProductId extends BaseId<UUID> {

    protected ProductId(UUID value) {
        super(value);
    }
}
