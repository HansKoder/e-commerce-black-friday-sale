package org.ecommerce.blackfriday.cart.application.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.entity.CartItem;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;

@ApplicationScoped
public class SaveCartItemService {

    private final CartRepository cartRepository;

    @Inject
    public SaveCartItemService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Uni<Cart> addCartItem (String customerId, CartItem cartItem) {
        return cartRepository.getCartByCustomer(customerId)
                .onItem().transform(optional -> optional.orElse(Cart.create()))
                .invoke(domain -> domain.addCartItem(cartItem))
                .call(domain -> cartRepository.save(customerId, domain));
    }
}
