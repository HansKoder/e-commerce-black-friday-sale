package org.ecommerce.blackfriday.procurement.domain.model.entity;

import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.common.domain.model.entity.Product;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseItemId;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.Quantity;

import java.util.Objects;

public class PurchaseItem extends BaseEntity<PurchaseItemId> {
    private final Product product;
    private final Quantity quantity;

    public PurchaseItem(Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
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
}
