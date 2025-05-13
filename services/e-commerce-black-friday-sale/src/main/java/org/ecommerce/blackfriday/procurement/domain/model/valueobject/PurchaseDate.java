package org.ecommerce.blackfriday.procurement.domain.model.valueobject;

import java.time.LocalDateTime;

public record PurchaseDate (LocalDateTime value) {
    public static PurchaseDate now() {
        return new PurchaseDate(LocalDateTime.now());
    }
}
