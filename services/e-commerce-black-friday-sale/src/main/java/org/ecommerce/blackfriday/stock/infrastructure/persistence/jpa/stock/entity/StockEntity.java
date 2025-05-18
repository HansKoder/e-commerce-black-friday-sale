package org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table
public class StockEntity {

    @Id
    private UUID id;
    private UUID productId;
    private int amount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "id=" + id +
                ", productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
