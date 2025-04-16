package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.adapter;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.mapper.RedisCartMapper;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartModel;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

public class CartRepositoryAdapter implements CartRepository {

    private final RedisTemplate<String, RedisCartModel> redisTemplate;

    public CartRepositoryAdapter(RedisTemplate<String, RedisCartModel> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<Cart> getCartByCustomer(String customerId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(customerId))
                .map(RedisCartMapper::toDomain);
    }

    @Override
    public void save(String customerId, Cart cart) {
        redisTemplate.opsForValue().set(customerId, RedisCartMapper.toRedisCart(cart));
    }
}
