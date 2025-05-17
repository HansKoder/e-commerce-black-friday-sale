package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public record CreatePurchaseRequest (
        LocalDateTime date,
        ProviderRequest provider,
        List<PurchaseItemRequest> items
) {
    @Override
    public String toString() {
        return "CreatePurchaseRequest{" +
                "date=" + date +
                ", provider=" + provider +
                ", items=" + items +
                '}';
    }
}
