package org.ecommerce.blackfriday.cart.interfaces.rest.common.mapper;

import org.ecommerce.blackfriday.cart.domain.model.entity.CartItem;
import org.ecommerce.blackfriday.cart.domain.model.entity.Product;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.ProductPrice;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.Quantity;
import org.ecommerce.blackfriday.cart.interfaces.rest.common.dto.CartItemResponse;
import org.ecommerce.blackfriday.cart.interfaces.rest.item.dto.SaveCartItemRequest;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;

import java.util.UUID;

public class CartItemMapper {

    public static CartItem toDomain (SaveCartItemRequest dto) {
        Product product = new Product.Builder()
                .withProductId(new ProductId(UUID.fromString(dto.getProductId())))
                .withPrice(new ProductPrice(new Money(dto.getPrice())))
                .build();

        return CartItem.create(product, new Quantity(dto.getCant()));
    }

    public static CartItemResponse toDTO (CartItem domain) {
        return new CartItemResponse(
                domain.getId().getValue().toString(),
                domain.getProduct().getId().getValue().toString(),
                domain.getProduct().getPrice().value().getAmount(),
                domain.getQuantity().getValue(),
                domain.getTotal().getAmount()
        );
    }

}
