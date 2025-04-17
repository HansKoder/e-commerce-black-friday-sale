package org.ecommerce.blackfriday.cart.interfaces.rest.cart.mapper;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartId;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.request.CreateCartRequest;
import org.ecommerce.blackfriday.cart.interfaces.rest.cart.dto.response.GetCartResponse;

import java.util.UUID;

public class CartMapper {

    public static Cart toDomain (CreateCartRequest dto) {
        CartId cartId = new CartId(UUID.randomUUID());

        Cart domain = new Cart();
        domain.setId(cartId);
        domain.addCartItem(CartItemMapper.toDomain(dto));

        return domain;
    }

    public static GetCartResponse toDto (Cart domain, String customerId) {
        return new GetCartResponse(
                customerId,
                domain.getId().toString(),
                domain.getCartItems().stream().map(CartItemMapper::toDTO).toList(),
                domain.getTotal()
        );
    }

}
