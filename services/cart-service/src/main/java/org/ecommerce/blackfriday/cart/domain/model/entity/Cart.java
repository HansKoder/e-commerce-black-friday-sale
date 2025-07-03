package org.ecommerce.blackfriday.cart.domain.model.entity;

import org.ecommerce.blackfriday.cart.domain.model.exception.CartItemNotFoundDomainException;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartId;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;

import java.math.BigDecimal;
import java.util.*;

public class Cart extends BaseEntity<CartId> {

    private final List<CartItem> cartItems;
    private BigDecimal total = BigDecimal.ZERO;

    private Cart() {
        cartItems = new ArrayList<>();
        setId(new CartId(UUID.randomUUID()));
    }

    private Cart (CartId cartId, List<CartItem> items) {
        this.cartItems = new ArrayList<>(items);
        setId(cartId);
        calculateTotal();
    }

    public static Cart create () {
        return new Cart();
    }

    public static Cart recreate (CartId cartId, List<CartItem> items) {
        return new Cart(cartId, items);
    }

    public void addCartItem (CartItem cartItem) {
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
        CartItem cartItem = getCartItemByID(uuid);
        total = total.subtract(cartItem.getSubTotal());
        cartItems.remove(cartItem);
    }

    public void incrementQuantity (String uuid) {
        CartItem cartItem = getCartItemByID(uuid);
        total = total.subtract(cartItem.getSubTotal());
        cartItem.incrementQuantity();
        total = total.add(cartItem.getSubTotal());
    }

    public void decrementQuantity (String uuid) {
        CartItem cartItem = getCartItemByID(uuid);
        total = total.subtract(cartItem.getSubTotal());
        cartItem.decrementQuantity();
        total = total.add(cartItem.getSubTotal());
    }

    public void updateQuantity (String uuid, int quantity) {
        CartItem cartItem = getCartItemByID(uuid);
        total = total.subtract(cartItem.getSubTotal());
        cartItem.updateQuantity(quantity);
        total = total.add(cartItem.getSubTotal());
    }

    public List<CartItem> getCartItems () {
        return List.copyOf(cartItems);
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
}
