package org.ecommerce.blackfriday.cart.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.entity.CartItem;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;

@ApplicationScoped
public class SaveCartItemService {

    private final CartRepository cartRepository;

    public SaveCartItemService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addCartItem (String customerId, CartItem cartItem) {
        CartLogger.info("Use Case Save Cart Item {}, customerId {}", cartItem, customerId);

        Cart domain = cartRepository.getCartByCustomer(customerId).orElse(Cart.create());
        CartLogger.info("Cart Domain after search in redis {}", domain);
        domain.addCartItem(cartItem);

        cartRepository.save(customerId, domain);
        return domain;
    }
}
