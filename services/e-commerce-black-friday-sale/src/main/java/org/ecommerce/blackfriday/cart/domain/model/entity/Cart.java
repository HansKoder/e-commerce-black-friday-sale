package org.ecommerce.blackfriday.cart.domain.model.entity;

import org.ecommerce.blackfriday.cart.domain.model.exception.CartItemNotFoundDomainException;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartId;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Cart extends BaseEntity<CartId> {

    private final List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addCartItem (CartItem cartItem) {
        this.cartItems.add(cartItem);
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

}
