package org.ecommerce.blackfriday.cart.interfaces.rest.item;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.cart.application.service.RemoveCartItemService;
import org.ecommerce.blackfriday.cart.application.service.SaveCartItemService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.infraestructure.rate.RateLimitingService;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.dto.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.exception.RateLimitExceededException;
import org.ecommerce.blackfriday.cart.interfaces.rest.item.dto.DeleteCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.item.dto.SaveCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartItemMapper;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart/items")
public class CartItemRestController {

    private final SaveCartItemService saveCartItemService;
    private final RemoveCartItemService removeCartItemService;
    private final RateLimitingService rateLimitingService;

    public CartItemRestController(
            SaveCartItemService service,
            RemoveCartItemService removeCartItemService, RateLimitingService rateLimitingService) {
        this.saveCartItemService = service;
        this.removeCartItemService = removeCartItemService;
        this.rateLimitingService = rateLimitingService;
    }

    @PostMapping("/save")
    ResponseEntity<GetCartResponse> addCartItem (@Valid @RequestBody SaveCartItemRequest cartDto) {

        System.out.println("Add Cart Item " + cartDto.toString());

        if (Objects.isNull(cartDto.getCustomerId())) throw new IllegalArgumentException("CustomerId is mandatory");

        if (!rateLimitingService.allowRequest(cartDto.getCustomerId())) {
            throw new RateLimitExceededException("The Service Save Item is not available, It has many request");
        }

        Cart domain = saveCartItemService
                .addCartItem(cartDto.getCustomerId(), CartItemMapper.toDomain(cartDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CartMapper.toDto(domain, cartDto.getCustomerId()));
    }

    @DeleteMapping("/delete")
    ResponseEntity<Void> removeCartItem (@Valid @RequestBody DeleteCartItemRequest deleteCartItemRequest) {
        removeCartItemService.removeCartByCustomerId(
                new CustomerId(UUID.fromString(deleteCartItemRequest.getCustomerId())),
                new CartItemId(UUID.fromString(deleteCartItemRequest.getCartItemId()))
        );

        return ResponseEntity.noContent().build();
    }

}
