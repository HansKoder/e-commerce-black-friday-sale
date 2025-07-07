package org.ecommerce.blackfriday.cart.domain.model.repository;

import io.smallrye.mutiny.Uni;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;

import java.util.Optional;

public interface CartRepository {
    Uni<Optional<Cart>> getCartByCustomer (String customerId);
    Uni<Void> save (String customerId, Cart cart);
}
