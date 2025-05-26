package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.strategy.purchaseprice;

import org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.strategy.criteria.PurchaseCriteria;

public record AveragePurchasePrice (PurchaseCriteria criteria) implements PurchasePriceStrategy {
}
