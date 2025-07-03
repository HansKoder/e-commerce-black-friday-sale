package org.ecommerce.blackfriday.cart.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartNotFoundException;

@ApplicationScoped
public class UpdateQuantityService {

    private final CartRepository cartRepository;

    public UpdateQuantityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart update (CustomerId customer, CartItemId cartItem, int quantity) {
        Cart domain = cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartNotFoundException(customer.getValue().toString()));

        domain.updateQuantity(cartItem.getValue().toString(), quantity);
        cartRepository.save(customer.getValue().toString(), domain);

        return domain;
    }
}
