package org.ecommerce.blackfriday.procurement.domain.model.repository;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;

public interface PurchaseRepository {
    void save(Purchase domain);
}
