package org.ecommerce.blackfriday.cart.domain.model.entity;

import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.ecommerce.blackfriday.common.domain.model.entity.Product;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.Quantity;
import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class CartItem extends BaseEntity<CartItemId> {

    private Product product;
    private Quantity quantity;

    private BigDecimal cachedTotal = BigDecimal.ZERO;
    private boolean isDirty = true;

    private CartItem (Builder builder) {
        product = builder.product;
        quantity = builder.quantity;
        setId(builder.id);
        markDirty();
    }

    @Deprecated
    private CartItem(Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
        this.setId(new CartItemId(UUID.randomUUID()));
        markDirty();
    }

    @Deprecated
    private CartItem(CartItemId cartItemId, Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
        this.setId(cartItemId);
        markDirty();
    }

    @Deprecated
    public static CartItem create (Product product, Quantity quantity) {
        return new CartItem(product, quantity);
    }

    @Deprecated
    public static CartItem recreate (CartItemId cartItemId, Product product, Quantity quantity) {
        return new CartItem(cartItemId, product, quantity);
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void incrementQuantity () {
        this.quantity = this.quantity.increment();
        markDirty();
    }

    public void decrementQuantity () {
        this.quantity = this.quantity.decrement();
        markDirty();
    }

    public void updateQuantity (int quantityUpdated) {
        this.quantity = this.quantity.set(quantityUpdated);
        markDirty();
    }

    private void markDirty() {
        isDirty = true;
    }

    public BigDecimal getSubTotal () {
        CartLogger.info("[CART] (DOMAIN) (step 1) method{cartItem.getSubtotal}, info [cartItem: {}]", this);

        if (isDirty) {
            cachedTotal = product.getPrice().value().multiply(quantity.value()).getAmount();
            isDirty = false;
            CartLogger.info("[CART] (DOMAIN) (step 2) compute subtotal, subtotal cached: {}", cachedTotal);
        }

        return cachedTotal;
    }

    public Money getTotal () {
        return product.getPrice().value().multiply(quantity.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product)
                && Objects.equals(quantity, cartItem.quantity)
                && Objects.equals(getId(), cartItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity, getId());
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", dirty=" + isDirty +
                ", subtotal=" + cachedTotal +
                '}';
    }

    public static final class Builder {
        private Product product;
        private Quantity quantity;
        private CartItemId id;

        private Builder() {
        }

        public static Builder aCartItem() {
            return new Builder();
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(Quantity quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder id(CartItemId id) {
            this.id = id;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }
}
