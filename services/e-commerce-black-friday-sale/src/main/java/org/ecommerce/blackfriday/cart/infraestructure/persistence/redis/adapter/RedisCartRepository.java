package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.adapter;

import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.infraestructure.CartLogger;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.mapper.RedisCartMapper;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RedisCartRepository implements CartRepository {

    private final RedisTemplate<String, RedisCartModel> redisTemplate;

    public RedisCartRepository(RedisTemplate<String, RedisCartModel> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<Cart> getCartByCustomer(String customerId) {
        CartLogger.info("Redis Cart Repository GET {}", customerId);
        return Optional.ofNullable(redisTemplate.opsForValue().get(customerId))
                .map(RedisCartMapper::toDomain);
    }

    @Override
    public void save(String customerId, Cart cart) {
        CartLogger.info("Redis Cart Repo Save {}, customerId {} ", cart, customerId);
        redisTemplate.opsForValue().set(customerId, RedisCartMapper.toRedisCart(cart));
    }
}
