package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.entity.CartItem;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.springframework.stereotype.Service;

@Service
public class SaveCartItemService {

    private final CartRepository cartRepository;

    public SaveCartItemService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addCartItem (String customerId, CartItem cartItem) {
        CartLogger.info("[CART] (USE CASE), (step 1), method{addCartItem}, info: [cartItem: {}, customerId: {}]", cartItem, customerId);

        Cart domain = cartRepository.getCartByCustomer(customerId).orElse(Cart.initCart());
        CartLogger.info("[CART] (USE CASE) (step 2), getCart from redis cluster or init cart, info: [domain: {}]", domain);
        domain.addCartItem(cartItem);

        cartRepository.save(customerId, domain);
        return domain;
    }
}
