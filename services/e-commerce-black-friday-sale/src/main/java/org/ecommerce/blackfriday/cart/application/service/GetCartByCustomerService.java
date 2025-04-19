package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartByCustomerNotFoundRestException;
import org.springframework.stereotype.Service;

@Service
public class GetCartByCustomerService {

    private final CartRepository cartRepository;

    public GetCartByCustomerService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCart (CustomerId customer) {
        return cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartByCustomerNotFoundRestException(customer.getValue().toString()));

    }
}
