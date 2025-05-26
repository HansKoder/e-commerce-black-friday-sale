package org.ecommerce.pricing.ms_pricing.costbaserefence.domain.model.valueobject.strategy.criteria;

import java.time.LocalDate;

public record DateRange (LocalDate from, LocalDate to) implements PurchaseCriteria{
}
