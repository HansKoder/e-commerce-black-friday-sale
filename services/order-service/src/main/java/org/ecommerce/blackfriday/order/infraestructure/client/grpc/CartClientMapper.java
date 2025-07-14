package org.ecommerce.blackfriday.order.infraestructure.client.grpc;

import org.ecommerce.blackfriday.order.infraestructure.client.model.CartItemModel;
import org.ecommerce.blackfriday.order.infraestructure.client.model.CartModel;

import java.math.BigDecimal;

public class CartClientMapper {

    public static CartModel fromResponseToEntity (org.ecommerce.blackfriday.cart.grpc.CartResponse response, String customerId) {
        return new CartModel(
                customerId,
                response.getCartId(),
                response.getItemsList()
                        .stream()
                        .map(CartClientMapper::fromResponseToEntity)
                        .toList(),
                new BigDecimal(response.getTotal())
        );
    }

    private static CartItemModel fromResponseToEntity (org.ecommerce.blackfriday.cart.grpc.CartItem response) {
        return new CartItemModel(
                response.getCartItemId(),
                response.getProductId(),
                new BigDecimal(response.getPrice()),
                response.getQuantity(),
                new BigDecimal(response.getTotal())
        );
    }

}
