package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.entity;

import jakarta.persistence.*;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseStatusJPA;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase_status_history")
public class PurchaseStatusHistoryEntity {

    @Id
    private UUID id;

    private UUID purchaseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "previous_status")
    private PurchaseStatusJPA prevStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status")
    private PurchaseStatusJPA newStatus;

    private String comment;

    private LocalDateTime date;

    public PurchaseStatusHistoryEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PurchaseStatusJPA getPrevStatus() {
        return prevStatus;
    }

    public void setPrevStatus(PurchaseStatusJPA prevStatus) {
        this.prevStatus = prevStatus;
    }

    public PurchaseStatusJPA getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(PurchaseStatusJPA newStatus) {
        this.newStatus = newStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(UUID purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public String toString() {
        return "PurchaseHistoryEntity{" +
                "id=" + id +
                ", prevStatus=" + prevStatus +
                ", newStatus=" + newStatus +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
