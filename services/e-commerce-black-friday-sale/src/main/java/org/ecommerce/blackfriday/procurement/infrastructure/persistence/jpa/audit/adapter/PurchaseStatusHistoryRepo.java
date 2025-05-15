package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.adapter;

import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.entity.PurchaseStatusHistoryEntity;

import java.util.List;
import java.util.UUID;

public interface PurchaseStatusHistoryRepo {
    void save(PurchaseStatusHistoryEntity entity);
    List<PurchaseStatusHistoryEntity> findByPurchaseId (UUID id);
}
