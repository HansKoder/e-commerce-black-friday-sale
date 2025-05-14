package org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "purchase_items")
public class PurchaseItemEntity {

    @Id
    private final UUID id;
    private final UUID productId;

    @Column(name = "COST_PER_UNIT")
    private final BigDecimal costPerUnit;

    private final int quantity;
    private final BigDecimal subtotal;

    public PurchaseItemEntity(UUID id, UUID productId, BigDecimal costPerUnit, int quantity, BigDecimal subtotal) {
        this.id = id;
        this.productId = productId;
        this.costPerUnit = costPerUnit;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}
