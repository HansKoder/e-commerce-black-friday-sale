package org.ecommerce.blackfriday.cart.domain.model.repository;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> getCartByCustomer (String customerId);
    void save (String customerId, Cart cart);
}
