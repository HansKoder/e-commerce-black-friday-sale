package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.adapter;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ecommerce.blackfriday.cart.domain.model.entity.Cart;
import org.ecommerce.blackfriday.cart.domain.model.repository.CartRepository;
import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartModel;

import java.util.Optional;

@ApplicationScoped
public class RedisCartRepository implements CartRepository {

    private final ValueCommands<String, RedisCartModel> commands;

    @Inject
    public RedisCartRepository(RedisDataSource redis) {
        this.commands = redis.value(String.class, RedisCartModel.class);
    }

    @Override
    public Optional<Cart> getCartByCustomer(String customerId) {
        return Optional.empty();
    }

    @Override
    public void save(String customerId, Cart cart) {

    }
}
