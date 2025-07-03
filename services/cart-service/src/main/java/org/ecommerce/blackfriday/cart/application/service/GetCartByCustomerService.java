package org.ecommerce.blackfriday.cart.application.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartNotFoundException;

@ApplicationScoped
public class GetCartByCustomerService {

    private final CartRepository cartRepository;

    public GetCartByCustomerService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Uni<Cart> getCart (CustomerId customer) {
        /*
        return cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartNotFoundException(customer.getValue().toString()));

         */

        return cartRepository.getCartByCustomer(customer.getValue().toString())
                .onItem().transform(optional -> optional.orElseThrow(() -> new CartNotFoundException(customer.getValue().toString())));
    }
}
