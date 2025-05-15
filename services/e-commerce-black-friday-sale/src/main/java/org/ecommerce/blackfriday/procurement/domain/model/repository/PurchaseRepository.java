package org.ecommerce.blackfriday.procurement.domain.model.repository;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseRepository {
    void save(Purchase domain);
    Optional<Purchase> findById (UUID uuid);
}
