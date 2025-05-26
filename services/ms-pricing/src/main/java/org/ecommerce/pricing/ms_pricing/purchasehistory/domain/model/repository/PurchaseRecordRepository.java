package org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.repository;

import org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.entity.PurchaseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PurchaseRecordRepository {
    List<PurchaseRecord> getByRangeDate (UUID productId, LocalDateTime from, LocalDateTime to);
    Page<PurchaseRecord> getByLimit (UUID productId, Pageable page);
    Optional<PurchaseRecord> getLast (UUID productId);
}
