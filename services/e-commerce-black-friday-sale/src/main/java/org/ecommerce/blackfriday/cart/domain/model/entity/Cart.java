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

    private Cart() {
        cartItems = new ArrayList<>();
        setId(new CartId(UUID.randomUUID()));
    }

    private Cart (CartId cartId, List<CartItem> items) {
        this.cartItems = new ArrayList<>(items);
        setId(cartId);
    }

    public static Cart create () {
        return new Cart();
    }

    public static Cart recreate (CartId cartId, List<CartItem> items) {
        return new Cart(cartId, items);
    }

    public void addCartItem (CartItem cartItem) {
        System.out.println("here....");
        System.out.println("stop...");
        System.out.println(cartItem.toString());

        this.cartItems.add(cartItem);
        System.out.println("Size items domain " + cartItems.size());
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
        cartItems.remove(cartItem);
    }

    public void incrementQuantity (String uuid) {
        CartItem cartItem = getCartItemByID(uuid);
        cartItem.incrementQuantity();
    }

    public void decrementQuantity (String uuid) {
        CartItem cartItem = getCartItemByID(uuid);
        cartItem.decrementQuantity();
    }

    public void updateQuantity (String uuid, int quantity) {
        CartItem cartItem = getCartItemByID(uuid);
        cartItem.updateQuantity(quantity);
    }

    public List<CartItem> getCartItems () {
        return List.copyOf(cartItems);
    }

    public BigDecimal getTotal () {
        return cartItems
                .stream()
                .map(item -> item.getTotal().getAmount())
                .reduce(Money.ZERO, BigDecimal::add);
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
