package org.ecommerce.blackfriday.cart.application.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;

@ApplicationScoped
public class MarkCartService {

    private final CartRepository cartRepository;

    public MarkCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Uni<Void> handler (String customerId) {
        return cartRepository.getCartByCustomer(customerId)
                .onItem().transform(optional -> optional.orElse(Cart.initCart()))
                .invoke(Cart::markOrderInProcess)
                .call(domain -> cartRepository.save(customerId, domain))
                .log()
                .replaceWithVoid();
    }
}
