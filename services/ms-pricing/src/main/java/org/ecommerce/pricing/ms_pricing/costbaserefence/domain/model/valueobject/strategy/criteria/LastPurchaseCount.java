package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.strategy.criteria;

import org.ecommerce.pricing.ms_pricing.shared.domain.model.valueobject.Money;

public record LastPurchaseCount (int count)  implements PurchaseCriteria, CalculatorCost {
    @Override
    public Money getCost() {
        return null;
    }
}
