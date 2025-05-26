package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.entity;

import org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.exception.MissingPurchasePriceStrategy;
import org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.CostReferenceId;
import org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.ProductId;
import org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.strategy.purchaseprice.PurchasePriceStrategy;
import org.ecommerce.pricing.ms_pricing.shared.domain.model.entity.AggregateRoot;
import org.ecommerce.pricing.ms_pricing.shared.domain.model.valueobject.Money;

import java.util.Objects;

public class CostBaseRefence extends AggregateRoot<CostReferenceId> {
    private ProductId productId;
    private PurchasePriceStrategy purchasePriceStrategy;

    public static CostBaseRefence Create () {
        return null;
    }

    public static CostBaseRefence rebuild () {
        return null;
    }

    public void defineStrategy (PurchasePriceStrategy strategy) {
        this.purchasePriceStrategy = strategy;
    }

    public Money getCost () {
        if (Objects.isNull(purchasePriceStrategy))
            throw new MissingPurchasePriceStrategy("Missing Purchase Price Strategy");

        return null;
    }

}
