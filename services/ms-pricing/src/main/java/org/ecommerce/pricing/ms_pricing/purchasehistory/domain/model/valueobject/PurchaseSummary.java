package org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.valueobject;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseSummary (UUID productId, BigDecimal price) {
}
