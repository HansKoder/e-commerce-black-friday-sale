package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.mapper;

import org.ecommerce.blackfriday.cart.domain.model.entity.CartItem;
import org.ecommerce.blackfriday.cart.domain.model.entity.Product;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartItemId;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.ProductPrice;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.Quantity;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartItemModel;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;

import java.math.BigDecimal;
import java.util.UUID;

public class RedisCartItemMapper {

    public static CartItem toDomain (RedisCartItemModel itemModel) {
        CartItemId cartItemId = new CartItemId(UUID.fromString(itemModel.cartId()));

        ProductId productId = new ProductId(UUID.fromString(itemModel.productId()));
        ProductPrice price = new ProductPrice(new Money(itemModel.productPrice()));

        Product product = new Product.Builder()
                .withProductId(productId)
                .withPrice(price)
                .build();

        return CartItem.recreate(cartItemId, product, new Quantity(itemModel.quantity()));
    }

    public static RedisCartItemModel toRedisCart (CartItem domain) {
        String cartId = domain.getId().getValue().toString();
        String productId = domain.getProduct().getId().getValue().toString();
        BigDecimal price = domain.getProduct().getPrice().value().getAmount();
        int quantity = domain.getQuantity().getValue();
        BigDecimal total = domain.getTotal().getAmount();

        return new RedisCartItemModel(cartId, productId, price, quantity, total);
    }
}
