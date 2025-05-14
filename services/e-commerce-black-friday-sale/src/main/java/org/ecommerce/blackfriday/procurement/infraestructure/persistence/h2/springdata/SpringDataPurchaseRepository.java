package org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.springdata;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPurchaseRepository extends JpaRepository<PurchaseEntity, UUID> {
}
