package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.entity.CartItem;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveCartItemService {

    private final CartRepository cartRepository;

    public SaveCartItemService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addCartItem (String customerId, CartItem cartItem) {
        System.out.println("Use Case Save Cart Item " + cartItem.toString());
        Cart domain = cartRepository.getCartByCustomer(customerId)
                .orElse(Cart.create());

        domain.addCartItem(cartItem);

        cartRepository.save(customerId, domain);

        return domain;
    }
}
