package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.exception.CartByCustomerNotFoundRestException;
import org.springframework.stereotype.Service;

@Service
public class RemoveCartItemService {

    private final CartRepository cartRepository;

    public RemoveCartItemService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void removeCartByCustomerId (CustomerId customer, CartItemId cartItemId) {
        Cart domain = cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartByCustomerNotFoundRestException(customer.getValue().toString()));

        domain.deleteCartItem(cartItemId.getValue().toString());

        cartRepository.save(customer.getValue().toString(), domain);
    }
}
