package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response;

import java.math.BigDecimal;

public record PurchaseItemResponse(
        String uuid,
        String productId,
        BigDecimal costPerUnit,
        int quantity,
        BigDecimal subtotal
) {
    @Override
    public String toString() {
        return "PurchaseItemResponse{" +
                "uuid='" + uuid + '\'' +
                ", productId='" + productId + '\'' +
                ", costPerUnit=" + costPerUnit +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
