package org.ecommerce.blackfriday.cart.application.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartNotFoundException;

@ApplicationScoped
public class DecrementQuantityService {

    private final CartRepository cartRepository;

    public DecrementQuantityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Uni<Cart> decrement (CustomerId customer, CartItemId cartItemId) {

        /**
        Cart domain = cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartNotFoundException(customer.getValue().toString()));

        domain.decrementQuantity(cartItemId.getValue().toString());

        this.cartRepository.save(customer.getValue().toString(), domain);

        return domain;
         */
        return cartRepository.getCartByCustomer(customer.getValue().toString())
                .onItem().transform(optionalCart ->
                        optionalCart.orElseThrow(() -> new CartNotFoundException(customer.getValue().toString())))
                .invoke(cart -> cart.decrementQuantity(cartItemId.getValue().toString()))
                .call(cart -> cartRepository.save(customer.getValue().toString(), cart));
    }
}
