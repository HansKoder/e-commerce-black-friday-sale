package org.ecommerce.blackfriday.cart.domain.model.entity;

import org.ecommerce.blackfriday.cart.domain.model.exception.CartItemNotFoundDomainException;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.common.domain.model.entity.BaseEntity;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Cart extends BaseEntity<CartId> {

    private Customer customer;
    private final List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    private void addCartItem (CartItem cartItem) {
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

    private void deleteCartItem (String uuid) {
        CartItem cartItem = getCartItemByID(uuid);
        cartItems.remove(cartItem);
    }

}
