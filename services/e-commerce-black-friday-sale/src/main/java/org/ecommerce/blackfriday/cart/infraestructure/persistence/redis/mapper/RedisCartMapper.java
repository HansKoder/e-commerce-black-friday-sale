package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.mapper;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartItemModel;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartModel;

import java.util.List;

public class RedisCartMapper {

    public static Cart toDomain (RedisCartModel cartModel) {
        Cart cart = new Cart();

        cartModel
                .items()
                .forEach(item -> cart
                        .addCartItem(RedisCartItemMapper.toDomain(item)));

        return cart;
    }

    public static RedisCartModel toRedisCart (Cart domain) {
        List<RedisCartItemModel> items = domain.getCartItems()
                .stream()
                .map(RedisCartItemMapper::toRedisCart)
                .toList();

        return new RedisCartModel(
                items,
                domain.getTotal()
        );
    }

}
