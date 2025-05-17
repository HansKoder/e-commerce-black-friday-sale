package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseItemRequest(
        @NotNull(message = "ProductId is mandatory")
        UUID productId,
        @DecimalMin(value = "1.0", message = "Cost per unit must be greater or equal to one dollar ($1.0)")
        BigDecimal costPerUnit,
        @Min(value = 1, message = "The quantity must be greater or equal to 1")
        int quantity
) {
        @Override
        public String toString() {
                return "PurchaseItemRequest{" +
                        "productId=" + productId +
                        ", costPerUnit=" + costPerUnit +
                        ", quantity=" + quantity +
                        '}';
        }
}
