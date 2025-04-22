package org.ecommerce.blackfriday.cart.interfaces.rest.cart;

import org.ecommerce.blackfriday.cart.application.service.GetCartByCustomerService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.dto.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/cart")
public class CartRestController {

    private final GetCartByCustomerService getCartByCustomerService;

    public CartRestController(GetCartByCustomerService getCartByCustomerService) {
        this.getCartByCustomerService = getCartByCustomerService;
    }

    @GetMapping("/customer/{customerId}")
    ResponseEntity<GetCartResponse> getCartByCustomerId (@PathVariable("customerId") String customerId) {
        Cart response = getCartByCustomerService.getCart(new CustomerId(UUID.fromString(customerId)));
        return ResponseEntity.ok(CartMapper.toDto(response, customerId));
    }

}
