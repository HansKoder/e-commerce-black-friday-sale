package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.strategy.purchaseprice;

import org.ecommerce.pricing.ms_pricing.shared.domain.model.valueobject.Money;

public record LastPurchasePrice (Money price) implements PurchasePriceStrategy { }
