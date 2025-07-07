package org.ecommerce.blackfriday.common.domain.event;

import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;

public record IncreaseStockEvent (ProductId productId, int quantity) {
    @Override
    public String toString() {
        return "IncreaseStockEvent{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
