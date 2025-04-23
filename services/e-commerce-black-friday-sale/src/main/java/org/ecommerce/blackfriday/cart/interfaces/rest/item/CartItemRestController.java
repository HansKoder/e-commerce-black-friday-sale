package org.ecommerce.blackfriday.cart.interfaces.rest.item;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import org.ecommerce.blackfriday.cart.application.service.RemoveCartItemService;
import org.ecommerce.blackfriday.cart.application.service.SaveCartItemService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.dto.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.item.dto.DeleteCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.item.dto.SaveCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartItemMapper;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("api/v1/cart/items")
public class CartItemRestController {

    private final SaveCartItemService saveCartItemService;
    private final RemoveCartItemService removeCartItemService;

    public CartItemRestController(
            SaveCartItemService service,
            RemoveCartItemService removeCartItemService) {
        this.saveCartItemService = service;
        this.removeCartItemService = removeCartItemService;
    }

    @PostMapping("/save")
    ResponseEntity<GetCartResponse> addCartItem (@Valid @RequestBody SaveCartItemRequest cartDto) {
        System.out.println("Add Cart Item " + cartDto.toString());
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
