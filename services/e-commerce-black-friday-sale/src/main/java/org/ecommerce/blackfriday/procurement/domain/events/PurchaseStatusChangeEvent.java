package org.ecommerce.blackfriday.procurement.domain.events;

import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseStatusJPA;

import java.time.LocalDateTime;
import java.util.UUID;

public class PurchaseStatusChangeEvent {

    private final UUID purchaseId;
    private final PurchaseStatusJPA newStatus;
    private final PurchaseStatusJPA oldStatus;
    private final String comment;
    private final LocalDateTime changedAt;

    private PurchaseStatusChangeEvent(Builder builder) {
        this.purchaseId = builder.purchaseId;
        this.newStatus = builder.newStatus;
        this.oldStatus = builder.oldStatus;
        this.comment = builder.comment;
        this.changedAt = builder.changedAt != null ? builder.changedAt : LocalDateTime.now();
    }

    public UUID getPurchaseId() {
        return purchaseId;
    }

    public PurchaseStatusJPA getNewStatus() {
        return newStatus;
    }

    public PurchaseStatusJPA getOldStatus() {
        return oldStatus;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public static class Builder {
        private final UUID purchaseId;
        private final PurchaseStatusJPA newStatus;
        private PurchaseStatusJPA oldStatus;
        private String comment;
        private LocalDateTime changedAt;

        public Builder(UUID purchaseId, PurchaseStatusJPA newStatus) {
            this.purchaseId = purchaseId;
            this.newStatus = newStatus;
        }

        public Builder oldStatus(PurchaseStatusJPA oldStatus) {
            this.oldStatus = oldStatus;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder changedAt(LocalDateTime changedAt) {
            this.changedAt = changedAt;
            return this;
        }

        public PurchaseStatusChangeEvent build() {
            return new PurchaseStatusChangeEvent(this);
        }
    }


}
