package org.ecommerce.blackfriday.procurement.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseDate;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseId;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseStatus;

import java.math.BigDecimal;
import java.util.List;

public class Purchase extends BaseEntity<PurchaseId> {
    private final Provider provider;
    private final PurchaseStatus status;
    private final PurchaseDate purchaseDate;
    private final List<PurchaseItem> items;

    private Purchase(Provider provider, PurchaseStatus status, PurchaseDate purchaseDate, List<PurchaseItem> items) {
        this.provider = provider;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.items = items;
    }

    public Provider getProvider() {
        return provider;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    public PurchaseDate getPurchaseDate() {
        return purchaseDate;
    }

    public List<PurchaseItem> getItems() {
        return items;
    }

    public static Purchase create (Provider provider, PurchaseDate date, List<PurchaseItem> items) {
        return new Purchase(provider, PurchaseStatus.CREATE, date, items);
    }

    public static Purchase create (Provider provider, List<PurchaseItem> items) {
        return new Purchase(provider, PurchaseStatus.CREATE, PurchaseDate.now(), items);
    }

    public static Purchase rebuild (Provider provider, PurchaseStatus status, PurchaseDate date, List<PurchaseItem> items) {
        return new Purchase(provider, status, date, items);
    }

    public BigDecimal getTotal () {
        return items.stream()
                .map(item -> item.getSubTotal().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
