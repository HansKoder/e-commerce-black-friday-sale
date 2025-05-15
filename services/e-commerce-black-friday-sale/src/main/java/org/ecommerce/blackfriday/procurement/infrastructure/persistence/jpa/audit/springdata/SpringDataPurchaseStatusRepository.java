package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.springdata;

import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.entity.PurchaseStatusHistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPurchaseStatusRepository extends CrudRepository<PurchaseStatusHistoryEntity, UUID> {
    List<PurchaseStatusHistoryEntity> findByPurchaseId (UUID uuid);
}
