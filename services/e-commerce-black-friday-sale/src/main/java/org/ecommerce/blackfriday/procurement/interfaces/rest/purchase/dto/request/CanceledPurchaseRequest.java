package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CanceledPurchaseRequest (
        @NotNull(message = "PurchaseId must be mandatory")
        @NotEmpty(message = "PurchaseId mest be completed ")
        String purchaseId,
        String comment
) {
        @Override
        public String toString() {
                return "CanceledPurchaseRequest{" +
                        "purchaseId='" + purchaseId + '\'' +
                        ", comment='" + comment + '\'' +
                        '}';
        }
}
