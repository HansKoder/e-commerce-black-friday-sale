package org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {

    @Id
    private UUID id;

    @Embedded
    private ProviderEmbeddable provider;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private PurchaseStatusJPA status;

    private LocalDateTime date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PurchaseItemEntity> items;

    public PurchaseEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ProviderEmbeddable getProvider() {
        return provider;
    }

    public void setProvider(ProviderEmbeddable provider) {
        this.provider = provider;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PurchaseStatusJPA getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatusJPA status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<PurchaseItemEntity> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemEntity> items) {
        this.items = items;
    }
}
