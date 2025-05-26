package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.strategy.purchaseprice;

public sealed interface PurchasePriceStrategy permits AveragePurchasePrice, LastPurchasePrice, HighestPurchasePrice  {
}
