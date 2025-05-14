package org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {

    @Id
    private final UUID id;

    @Embedded
    private ProviderEmbeddable provider;

    private final BigDecimal total;
    private final PurchaseStatusJPA status;
    private final LocalDateTime date;

    @OneToMany
    private final List<PurchaseItemEntity> items;

    public PurchaseEntity(
            UUID id,
            BigDecimal total,
            PurchaseStatusJPA status,
            LocalDateTime date,
            List<PurchaseItemEntity> items) {
        this.id = id;
        this.total = total;
        this.status = status;
        this.date = date;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public PurchaseStatusJPA getStatus() {
        return status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<PurchaseItemEntity> getItems() {
        return items;
    }

    public ProviderEmbeddable getProvider() {
        return provider;
    }
}
