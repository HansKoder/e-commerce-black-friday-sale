package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartByCustomerNotFoundRestException;
import org.springframework.stereotype.Service;

@Service
public class GetCartEnabledService {

    // status added ok.
    // proto - generated code ok
    // server - pending
    // use case - getCartEnabled - pending
    // before continuing test all use cases - ok
    // add correlationId - ok

    private final CartRepository cartRepository;

    public GetCartEnabledService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart handler (String customerId) {
        Cart domain =  cartRepository.getCartByCustomer(customerId)
                .orElseThrow(() -> new CartByCustomerNotFoundRestException(customerId));

        domain.cartIsEnabled();
        return domain;
    }
}
