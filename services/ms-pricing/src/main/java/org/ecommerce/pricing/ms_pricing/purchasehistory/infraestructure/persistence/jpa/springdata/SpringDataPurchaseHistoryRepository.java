package org.ecommerce.pricing.ms_pricing.purchasehistory.infraestructure.persistence.jpa.springdata;

import org.ecommerce.pricing.ms_pricing.purchasehistory.infraestructure.persistence.jpa.entity.PurchaseRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPurchaseHistoryRepository extends JpaRepository<PurchaseRecordEntity, UUID> {

    List<PurchaseRecordEntity> findByProductIdAndReceivedAtBetween(UUID productId, LocalDateTime from, LocalDateTime to);

    Page<PurchaseRecordEntity> findByProductIdOrderByReceivedAtDesc(UUID productId, Pageable pageable);

    Optional<PurchaseRecordEntity> findFirstByProductIdOrderByReceivedAtDesc(UUID productId);

}
