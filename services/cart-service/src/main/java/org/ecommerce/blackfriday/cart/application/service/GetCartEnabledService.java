package org.ecommerce.blackfriday.cart.application.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartNotFoundException;

@ApplicationScoped
public class GetCartEnabledService {

    private final CartRepository cartRepository;

    public GetCartEnabledService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Uni<Cart> handler (String customerId) {
        return cartRepository.getCartByCustomer(customerId)
                .onItem()
                .transform(optional -> optional.orElseThrow(() -> new CartNotFoundException(customerId)))
                .invoke(Cart::cartIsEnabled);
    }
}
