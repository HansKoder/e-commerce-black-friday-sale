package org.ecommerce.blackfriday.procurement.domain.model.valueobject;

import org.ecommerce.blackfriday.common.domain.model.valueobject.BaseId;
import java.util.UUID;

public class ProviderId extends BaseId<UUID> {
    protected ProviderId(UUID value) {
        super(value);
    }
}
