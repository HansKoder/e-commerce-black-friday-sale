package org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.entity;

import org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.valueobject.PurchaseRecordId;
import org.ecommerce.pricing.ms_pricing.shared.domain.model.entity.AggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PurchaseRecord extends AggregateRoot<PurchaseRecordId> {

    private UUID purchaseId;
    private UUID productId;
    private LocalDateTime receivedAt;
    private BigDecimal price;

    private PurchaseRecord(PurchaseRecordId id, UUID purchaseId, UUID productId, LocalDateTime receivedAt, BigDecimal price) {
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.receivedAt = receivedAt;
        this.price = price;
        this.setId(id);
    }

    public static PurchaseRecord create (UUID purchaseId, UUID productId, LocalDateTime receivedAt, BigDecimal price) {
        return new PurchaseRecord(
                new PurchaseRecordId(UUID.randomUUID()),
                purchaseId,
                productId,
                receivedAt,
                price
        );
    }

    public static PurchaseRecord rebuild (PurchaseRecordId id, UUID purchaseId, UUID productId, LocalDateTime receivedAt, BigDecimal price) {
        return new PurchaseRecord(
                id,
                purchaseId,
                productId,
                receivedAt,
                price
        );
    }


}
