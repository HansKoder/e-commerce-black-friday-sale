package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartByCustomerNotFoundRestException;
import org.springframework.stereotype.Service;

@Service
public class UpdateQuantityService {

    private final CartRepository cartRepository;

    public UpdateQuantityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart update (CustomerId customer, CartItemId cartItem, int quantity) {
        Cart domain = cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartByCustomerNotFoundRestException(customer.getValue().toString()));

        domain.updateQuantity(cartItem.getValue().toString(), quantity);
        cartRepository.save(customer.getValue().toString(), domain);

        return domain;
    }
}
