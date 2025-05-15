package org.ecommerce.blackfriday.procurement.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.common.domain.model.entity.Product;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseItemId;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.Quantity;

import java.util.Objects;
import java.util.UUID;

public class PurchaseItem extends BaseEntity<PurchaseItemId> {
    private final Product product;
    private final Quantity quantity;

    private PurchaseItem(PurchaseItemId uuid, Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
        super.setId(uuid);
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getSubTotal () {
        return product.getPrice().value()
                .multiply(quantity.getValue());
    }

    public static PurchaseItem create (Product product, Quantity quantity) {
        PurchaseItemId uuid = new PurchaseItemId(UUID.randomUUID());
        return new PurchaseItem(uuid, product, quantity);
    }

    public static PurchaseItem recreate (PurchaseItemId uuid, Product product, Quantity quantity) {
        return new PurchaseItem(uuid, product, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PurchaseItem that = (PurchaseItem) o;
        return Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity);
    }

    @Override
    public String toString() {
        return "PurchaseItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
