package org.ecommerce.blackfriday.cart.interfaces.rest.cart;

import org.ecommerce.blackfriday.cart.application.service.GetCartByCustomerService;
import org.ecommerce.blackfriday.cart.application.service.MarkCartService;
import org.ecommerce.blackfriday.cart.application.service.UnMarkCartService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.dto.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartMapper;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart")
public class CartRestController {

    private final GetCartByCustomerService getCartByCustomerService;
    private final MarkCartService markCartService;
    private final UnMarkCartService unMarkCartService;

    public CartRestController(
            GetCartByCustomerService getCartByCustomerService,
            MarkCartService markCartService,
            UnMarkCartService unMarkCartService) {
        this.getCartByCustomerService = getCartByCustomerService;
        this.markCartService = markCartService;
        this.unMarkCartService = unMarkCartService;
    }

    @GetMapping("/customer/{customerId}")
    ResponseEntity<GetCartResponse> getCartByCustomerId (@PathVariable("customerId") String customerId) {
        CartLogger.info("[CART] [REST], method{getCartByCustomerId}, customerId:{}", customerId);
        Cart response = getCartByCustomerService.getCart(new CustomerId(UUID.fromString(customerId)));
        return ResponseEntity.ok(CartMapper.toDto(response, customerId));
    }

    @PutMapping("/mark/{customerId}")
    ResponseEntity<Void> markCart (@PathVariable("customerId") String customerId) {
        CartLogger.info("[CART] [REST], method{markCart}, customerId:{}", customerId);
        markCartService.handler(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/unmark/{customerId}")
    ResponseEntity<Void> unMarkCart (@PathVariable("customerId") String customerId) {
        CartLogger.info("[CART] [REST], method{unMarkCart}, customerId:{}", customerId);
        unMarkCartService.handler(customerId);
        return ResponseEntity.noContent().build();
    }

}
