package org.ecommerce.blackfriday.cart.interfaces.rest.cart;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.cart.application.service.CreateCartService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.request.CreateCartRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.response.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.mapper.CartMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/shopping-cart")
public class CartRestController {

    private final CreateCartService service;

    public CartRestController(CreateCartService service) {
        this.service = service;
    }

    @PostMapping("/create-cart")
    ResponseEntity<GetCartResponse> createCart (@Valid @RequestBody CreateCartRequest cartDto) {
        Cart domain = service.handler(cartDto.getCustomerId(), CartMapper.toDomain(cartDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CartMapper.toDto(domain, cartDto.getCustomerId()));
    }


}
