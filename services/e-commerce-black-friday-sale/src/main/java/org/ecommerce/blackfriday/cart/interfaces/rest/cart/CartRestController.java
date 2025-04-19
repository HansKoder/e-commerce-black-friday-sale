package org.ecommerce.blackfriday.cart.interfaces.rest.cart;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.cart.application.service.RemoveCartItemService;
import org.ecommerce.blackfriday.cart.application.service.SaveCartItemService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.request.DeleteCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.request.SaveCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.response.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.mapper.CartItemMapper;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.mapper.CartMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/shopping-cart")
public class CartRestController {

    private final SaveCartItemService service;

    private final RemoveCartItemService removeCartItemService;

    public CartRestController(SaveCartItemService service, RemoveCartItemService removeCartItemService) {
        this.service = service;
        this.removeCartItemService = removeCartItemService;
    }

    @PostMapping("/save-cart-item")
    ResponseEntity<GetCartResponse> addCartItem (@Valid @RequestBody SaveCartItemRequest cartDto) {
        Cart domain = service.addCartItem(cartDto.getCustomerId(), CartItemMapper.toDomain(cartDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CartMapper.toDto(domain, cartDto.getCustomerId()));
    }

    @DeleteMapping("delete-cart-item")
    ResponseEntity<Void> removeCartItem (@Valid @RequestBody DeleteCartItemRequest deleteCartItemRequest) {
        removeCartItemService.removeCartByCustomerId(
                new CustomerId(UUID.fromString(deleteCartItemRequest.getCustomerId())),
                new CartItemId(UUID.fromString(deleteCartItemRequest.getCartItemId()))
        );

        return ResponseEntity.noContent().build();
    }

}
