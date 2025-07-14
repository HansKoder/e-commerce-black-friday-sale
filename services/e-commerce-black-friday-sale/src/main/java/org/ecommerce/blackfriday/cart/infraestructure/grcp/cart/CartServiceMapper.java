package org.ecommerce.blackfriday.cart.infraestructure.grcp.cart;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.grpc.CartItem;
import org.ecommerce.blackfriday.cart.grpc.CartResponse;

import java.util.List;

public class CartServiceMapper {

    public static CartResponse fromDomainToResponse (Cart domain) {
        return CartResponse.newBuilder()
                .addAllItems(getListItemResponse(domain))
                .setCartId(domain.getId().getValue().toString())
                .setTotal(domain.getTotal().toString())
                .build();
    }

    private static List<CartItem> getListItemResponse (Cart domain) {
        return domain.getCartItems()
                .stream()
                .map(CartServiceMapper::fromDomainToResponse)
                .toList();
    }

    private static CartItem fromDomainToResponse (org.ecommerce.blackfriday.cart.domain.model.entity.CartItem domainItem) {
        return CartItem.newBuilder()
                .setCartItemId(domainItem.getId().getValue().toString())
                .setProductId(domainItem.getProduct().getId().getValue().toString())
                .setPrice(domainItem.getProduct().getPrice().value().getAmount().toString())
                .setQuantity(domainItem.getQuantity().value())
                .setTotal(domainItem.getTotal().getAmount().toString())
                .build();
    }
}
