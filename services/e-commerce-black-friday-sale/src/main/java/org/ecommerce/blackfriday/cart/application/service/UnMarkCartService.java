package org.ecommerce.blackfriday.cart.application.service;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartByCustomerNotFoundRestException;
import org.springframework.stereotype.Service;

@Service
public class UnMarkCartService {
    private final CartRepository cartRepository;

    public UnMarkCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void handler (String customerId) {
        Cart domain =  cartRepository.getCartByCustomer(customerId)
                .orElseThrow(() -> new CartByCustomerNotFoundRestException(customerId));

        domain.unMarkOrderInProcess();

        cartRepository.save(customerId, domain);
    }
}
