package org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.template;

import org.ecommerce.blackfriday.cart.infraestructure.persistence.redis.model.RedisCartModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCartTemplate {

    @Bean
    public RedisTemplate<String, RedisCartModel> redisCartTemplate (RedisConnectionFactory factory) {
        RedisTemplate<String, RedisCartModel> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisCartModel.class));
        return template;
    }

}
