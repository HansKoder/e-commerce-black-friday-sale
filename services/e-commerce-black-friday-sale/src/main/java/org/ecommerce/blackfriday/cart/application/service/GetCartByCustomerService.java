package org.ecommerce.blackfriday.cart.application.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.CartByCustomerNotFoundRestException;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.TimeoutException;
import org.springframework.stereotype.Service;

@Service
public class GetCartByCustomerService {

    private final CartRepository cartRepository;

    public GetCartByCustomerService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Retry(name = "getCartService", fallbackMethod = "fallbackCart")
    @CircuitBreaker(name = "getCartService", fallbackMethod = "fallbackCart")
    public Cart getCart (CustomerId customer) {
        return cartRepository.getCartByCustomer(customer.getValue().toString())
                .orElseThrow(() -> new CartByCustomerNotFoundRestException(customer.getValue().toString()));

    }

    public Cart fallbackCart(CustomerId customer, Throwable ex) {
        System.out.printf("Fallback is activated for this method %s: %s", customer.getValue(), ex.getMessage());
        throw new TimeoutException("Timeout, service is not working ");
    }
}
