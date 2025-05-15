package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.springdata;

import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPurchaseRepository extends JpaRepository<PurchaseEntity, UUID> {
}
