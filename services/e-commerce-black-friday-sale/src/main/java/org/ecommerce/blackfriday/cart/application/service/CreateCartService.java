package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCartService {

    private final CartRepository cartRepository;

    public CreateCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart handler (String customerId, Cart cart) {
        this.cartRepository.save(customerId, cart);
        return cart;
    }
}
