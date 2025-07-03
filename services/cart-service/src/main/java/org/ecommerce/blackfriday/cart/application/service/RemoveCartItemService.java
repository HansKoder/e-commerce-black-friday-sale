package org.ecommerce.blackfriday.cart.application.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartNotFoundException;

@ApplicationScoped
public class RemoveCartItemService {

    private final CartRepository cartRepository;

    @Inject
    public RemoveCartItemService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Uni<Void> removeCartByCustomerId (CustomerId customer, CartItemId cartItemId) {
        /*
        Cart domain = cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartNotFoundException(customer.getValue().toString()));

        domain.deleteCartItem(cartItemId.getValue().toString());

        cartRepository.save(customer.getValue().toString(), domain);
         */

        return cartRepository.getCartByCustomer(customer.getValue().toString())
                .onItem().transform(optionalCart ->
                        optionalCart.orElseThrow(() -> new CartNotFoundException(customer.getValue().toString())))
                .invoke(cart -> cart.deleteCartItem(cartItemId.getValue().toString()))
                .call(cart -> cartRepository.save(customer.getValue().toString(), cart))
                .replaceWithVoid();
    }
}
