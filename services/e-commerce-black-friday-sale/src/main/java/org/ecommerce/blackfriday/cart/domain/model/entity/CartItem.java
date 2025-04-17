package org.ecommerce.blackfriday.cart.domain.model.entity;

import org.ecommerce.blackfriday.cart.domain.model.valueobject.Quantity;
import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;

import java.util.Objects;

public class CartItem extends BaseEntity<CartItemId> {

    private final Product product;
    private final Quantity quantity;

    public CartItem(Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void incrementQuantity () {
        this.quantity.increment();
    }

    public void decrementQuantity () {
        this.quantity.decrement();
    }

    public void updateQuantity (int quantityUpdated) {
        this.quantity.set(quantityUpdated);
    }

    public Money getTotal () {
        return product.getPrice().value().multiply(quantity.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product) && Objects.equals(quantity, cartItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity);
    }
}
