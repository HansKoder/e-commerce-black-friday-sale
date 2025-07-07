package org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.dto.GetCartResponse;

public class CartMapper {

    public static GetCartResponse toDto (Cart domain, String customerId) {
        return new GetCartResponse(
                customerId,
                domain.getId().getValue().toString(),
                domain.getCartItems().stream().map(CartItemMapper::toDTO).toList(),
                domain.getTotal()
        );
    }

}
