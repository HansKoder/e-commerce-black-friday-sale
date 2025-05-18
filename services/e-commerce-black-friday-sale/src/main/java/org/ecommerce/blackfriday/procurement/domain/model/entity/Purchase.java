package org.ecommerce.blackfriday.procurement.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.procurement.domain.model.exception.InvalidStatusPurchaseDomainException;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseDate;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseId;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Purchase extends BaseEntity<PurchaseId> {
    private final Provider provider;
    private PurchaseStatus status;
    private final PurchaseDate purchaseDate;
    private final List<PurchaseItem> items;


    private Purchase(PurchaseId uuid, Provider provider, PurchaseStatus status, PurchaseDate purchaseDate, List<PurchaseItem> items) {
        this.provider = provider;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.items = items;
        super.setId(uuid);
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
        return new Purchase(new PurchaseId(UUID.randomUUID()), provider, PurchaseStatus.CREATE, date, items);
    }

    public static Purchase rebuild (PurchaseId uuid, Provider provider, PurchaseStatus status, PurchaseDate date, List<PurchaseItem> items) {
        return new Purchase(uuid, provider, status, date, items);
    }

    private String msgInvalidStatus (String fun) {
        return "To " + fun + " this purchase with the ID " + getId().getValue() + ", must have a status [CREATED] valid";
    }

    public void canceled () {
        System.out.println("[DOMAIN] canceled");
        if (!status.equals(PurchaseStatus.CREATE) )
            throw new InvalidStatusPurchaseDomainException(msgInvalidStatus("[CANCELED]"));

        status = PurchaseStatus.CANCELED;
    }

    public void received () {
        System.out.println("[DOMAIN] canceled");
        if (!status.equals(PurchaseStatus.CREATE) )
            throw new InvalidStatusPurchaseDomainException(msgInvalidStatus("[RECEIVED]"));

        status = PurchaseStatus.RECEIVED;
    }

    public BigDecimal getTotal () {
        return items.stream()
                .map(item -> item.getSubTotal().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "provider=" + provider +
                ", status=" + status +
                ", purchaseDate=" + purchaseDate +
                ", items=" + items +
                '}';
    }
}
