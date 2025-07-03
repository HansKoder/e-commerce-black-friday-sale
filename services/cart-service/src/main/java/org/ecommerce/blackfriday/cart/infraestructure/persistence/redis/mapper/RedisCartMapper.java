package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.mapper;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.entity.CartItem;
import org.ecommerce.blackfriday.cart.domain.model.valueobject.CartId;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartItemModel;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartModel;

import java.util.List;
import java.util.UUID;

public class RedisCartMapper {

    public static Cart toDomain (RedisCartModel cartModel) {
        CartLogger.info("Redis Cart Mapper, cartModel {}", cartModel.toString());
        List<CartItem> items = cartModel.items()
                .stream()
                .map(RedisCartItemMapper::toDomain)
                .toList();

        CartId cartId = new CartId(UUID.fromString(cartModel.cartId()));

        return Cart.recreate(cartId, items);
    }

    public static RedisCartModel toRedisCart (Cart domain) {
        CartLogger.info("Redis Cart Mapper,toEntity to Redis {}", domain.toString());
        List<RedisCartItemModel> items = domain.getCartItems()
                .stream()
                .map(RedisCartItemMapper::toRedisCart)
                .toList();

        return new RedisCartModel(
                domain.getId().getValue().toString(),
                items,
                domain.getTotal()
        );
    }

}
