package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartByCustomerNotFoundRestException;
import org.springframework.stereotype.Service;

@Service
public class IncrementQuantityService {

    private final CartRepository cartRepository;

    public IncrementQuantityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart incrementQuantity (CustomerId customer, CartItemId cartItemId) {
        Cart domain = cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartByCustomerNotFoundRestException(customer.getValue().toString()));

        domain.incrementQuantity(cartItemId.getValue().toString());

        this.cartRepository.save(customer.getValue().toString(), domain);

        return domain;
    }
}
