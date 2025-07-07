package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.adapter;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.value.ReactiveValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.mapper.RedisCartMapper;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartModel;

import java.util.Optional;

@ApplicationScoped
public class RedisCartRepository implements CartRepository {

    private final ReactiveValueCommands<String, RedisCartModel> commands;

    @Inject
    public RedisCartRepository(ReactiveRedisDataSource redis) {
        this.commands = redis.value(String.class, RedisCartModel.class);
    }

    @Override
    public Uni<Optional<Cart>> getCartByCustomer(String customerId) {
        return commands.get(customerId)
                .onItem()
                .transform(redis -> Optional.ofNullable(redis).map(RedisCartMapper::toDomain));
    }

    @Override
    public Uni<Void> save(String customerId, Cart cart) {
        return commands.setex(customerId, 3600, RedisCartMapper.toRedisCart(cart));
    }
}
