package org.ecommerce.blackfriday.cart.interfaces.rest.quantity;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.cart.application.service.DecrementQuantityService;
import org.ecommerce.blackfriday.cart.application.service.IncrementQuantityService;
import org.ecommerce.blackfriday.cart.application.service.UpdateQuantityService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.quantity.dto.IncrementQuantityRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.dto.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartMapper;
import org.ecommerce.blackfriday.cart.interfaces.rest.quantity.dto.UpdateQuantityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cart/item/quantity")
public class ItemQuantityRestController {

    private final IncrementQuantityService incrementQuantityService;
    private final DecrementQuantityService decrementQuantityService;
    private final UpdateQuantityService updateQuantityService;

    public ItemQuantityRestController(
            IncrementQuantityService incrementQuantityService,
            DecrementQuantityService decrementQuantityService,
            UpdateQuantityService updateQuantityService) {
        this.incrementQuantityService = incrementQuantityService;
        this.decrementQuantityService = decrementQuantityService;
        this.updateQuantityService = updateQuantityService;
    }

    @PutMapping("increment")
    ResponseEntity<GetCartResponse> increment (
            @Valid @RequestBody IncrementQuantityRequest request) {
        Cart response = incrementQuantityService.incrementQuantity(
                new CustomerId(UUID.fromString(request.customerId())),
                new CartItemId(UUID.fromString(request.cartItemId()))
        );

        return ResponseEntity.ok(CartMapper.toDto(response, request.customerId()));
    }

    @PutMapping("decrement")
    ResponseEntity<GetCartResponse> decrement (
            @Valid @RequestBody IncrementQuantityRequest request) {
        Cart response = decrementQuantityService.decrement(
                new CustomerId(UUID.fromString(request.customerId())),
                new CartItemId(UUID.fromString(request.cartItemId()))
        );

        return ResponseEntity.ok(CartMapper.toDto(response, request.customerId()));
    }

    @PutMapping("update")
    ResponseEntity<GetCartResponse> update (
            @Valid @RequestBody UpdateQuantityRequest request) {
        Cart response = updateQuantityService.update(
                new CustomerId(UUID.fromString(request.customerId())),
                new CartItemId(UUID.fromString(request.cartItemId())),
                request.quantity()
        );

        return ResponseEntity.ok(CartMapper.toDto(response, request.customerId()));
    }


}
