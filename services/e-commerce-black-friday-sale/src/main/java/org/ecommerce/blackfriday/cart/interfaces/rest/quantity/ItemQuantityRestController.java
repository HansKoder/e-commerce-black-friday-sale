package org.ecommerce.blackfriday.cart.interfaces.rest.quantity;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.cart.application.service.CartItemIncrementQuantityService;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CustomerId;
import org.ecommerce.blackfriday.cart.interfaces.rest.quantity.dto.IncrementQuantityCartItemRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.dto.GetCartResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper.CartMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/cart/item/quantity")
public class ItemQuantityRestController {

    private final CartItemIncrementQuantityService quantityService;

    public ItemQuantityRestController(CartItemIncrementQuantityService quantityService) {
        this.quantityService = quantityService;
    }

    @PutMapping("increment")
    ResponseEntity<GetCartResponse> increment (
            @Valid @RequestBody IncrementQuantityCartItemRequest request) {
        Cart response = quantityService.incrementQuantity(
                new CustomerId(UUID.fromString(request.getCustomerId())),
                new CartItemId(UUID.fromString(request.getCartItemId()))
        );

        return ResponseEntity.ok(CartMapper.toDto(response, request.getCustomerId()));
    }

}
