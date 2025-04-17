package org.ecommerce.blackfriday.cart.interfaces.rest.cart;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.cart.application.service.SaveCartItemService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.request.SaveCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.response.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.mapper.CartItemMapper;
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

    private final SaveCartItemService service;

    public CartRestController(SaveCartItemService service) {
        this.service = service;
    }

    @PostMapping("/save-cart")
    ResponseEntity<GetCartResponse> addCartItem (@Valid @RequestBody SaveCartItemRequest cartDto) {
        Cart domain = service.addCartItem(cartDto.getCustomerId(), CartItemMapper.toDomain(cartDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CartMapper.toDto(domain, cartDto.getCustomerId()));
    }


}
