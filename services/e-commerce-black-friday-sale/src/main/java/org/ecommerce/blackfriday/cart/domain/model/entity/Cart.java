package org.ecommerce.blackfriday.cart.domain.model.entity;

import org.ecommerce.blackfriday.cart.domain.model.exception.CartItemNotFoundDomainException;
import org.ecommerce.blackfriday.cart.domain.model.exception.OrderInProcessException;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartStatus;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartId;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;

import java.math.BigDecimal;
import java.util.*;

public class Cart extends BaseEntity<CartId> {

    private final List<CartItem> cartItems;
    private BigDecimal total;
    private CartStatus status;

    private Cart (Builder builder) {
        setId(builder.id);
        cartItems = builder.cartItems;
        status = builder.status;
        total = builder.total;
    }

    @Deprecated
    private Cart() {
        cartItems = new ArrayList<>();
        setId(new CartId(UUID.randomUUID()));
    }

    @Deprecated
    private Cart (CartId cartId, List<CartItem> items, boolean order) {
        this.cartItems = new ArrayList<>(items);
        setId(cartId);
        calculateTotal();
    }

    @Deprecated
    public static Cart create () {
        return new Cart();
    }

    @Deprecated
    public static Cart recreate (CartId cartId, List<CartItem> items, boolean order) {
        return new Cart(cartId, items, order);
    }

    public static Cart initCart () {
        CartLogger.info("[CART] (DOMAIN) method{initCart}");
        return new Builder()
                .id(new CartId(UUID.randomUUID()))
                .status(CartStatus.CART)
                .total(BigDecimal.ZERO)
                .cartItems(new ArrayList<>())
                .build();
    }

    public void addCartItem (CartItem cartItem) {
        CartLogger.info("[CART] (DOMAIN) method{addCartItem}, info [item: {}]", cartItem);

        cartIsEnabled();

        this.cartItems.add(cartItem);
        total = total.add(cartItem.getSubTotal());
    }

    private CartItem getCartItemByID (String uuid) {
        CartItemId cartItemId = new CartItemId(UUID.fromString(uuid));

        Optional<CartItem> cartItemOptional = cartItems
                .stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst();

        if (cartItemOptional.isEmpty())
            throw new CartItemNotFoundDomainException("Cart Item with the ID " + uuid + " does not exist");

        return cartItemOptional.get();
    }

    public void deleteCartItem (String uuid) {
        cartIsEnabled();

        CartItem cartItem = getCartItemByID(uuid);
        total = total.subtract(cartItem.getSubTotal());
        cartItems.remove(cartItem);
    }

    public void incrementQuantity (String uuid) {
        cartIsEnabled();

        CartItem cartItem = getCartItemByID(uuid);
        total = total.subtract(cartItem.getSubTotal());
        cartItem.incrementQuantity();
        total = total.add(cartItem.getSubTotal());
    }

    public void decrementQuantity (String uuid) {
        cartIsEnabled();

        CartItem cartItem = getCartItemByID(uuid);
        total = total.subtract(cartItem.getSubTotal());
        cartItem.decrementQuantity();
        total = total.add(cartItem.getSubTotal());
    }

    public void updateQuantity (String uuid, int quantity) {
        cartIsEnabled();

        CartItem cartItem = getCartItemByID(uuid);
        total = total.subtract(cartItem.getSubTotal());
        cartItem.updateQuantity(quantity);
        total = total.add(cartItem.getSubTotal());
    }

    public List<CartItem> getCartItems () {
        return List.copyOf(cartItems);
    }

    public CartStatus getStatus() {
        return status;
    }

    public void calculateTotal () {
        total = cartItems
                .stream()
                .map(item -> item.getTotal().getAmount())
                .reduce(Money.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal () {
        return total;
    }

    public void cartIsEnabled () {
        CartLogger.info("[CART] (DOMAIN) method{cartIsEnabled}, info [id: {}, status: {}]", getId().getValue().toString(), status.name());
        if (!status.equals(CartStatus.CART))
            throw new OrderInProcessException("The cart id " + getId().getValue().toString() + " is in order process");
    }

    public void markOrderInProcess () {
        if (status.equals(CartStatus.CART))
            throw new OrderInProcessException("The cart id " + getId().getValue().toString() + " already has an order in process");

        status = CartStatus.ORDER_PROCESS;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartItems, cart.cartItems) && Objects.equals(getId(), cart.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cartItems, getId());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                ", total=" + total +
                ", status=" + status +
                '}';
    }

    public static final class Builder {
        private List<CartItem> cartItems;
        private BigDecimal total;
        private CartStatus status;
        private CartId id;

        private Builder() {
        }

        public static Builder aCart() {
            return new Builder();
        }

        public Builder cartItems(List<CartItem> cartItems) {
            this.cartItems = cartItems;
            return this;
        }

        public Builder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Builder status(CartStatus status) {
            this.status = status;
            return this;
        }

        public Builder id(CartId id) {
            this.id = id;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
