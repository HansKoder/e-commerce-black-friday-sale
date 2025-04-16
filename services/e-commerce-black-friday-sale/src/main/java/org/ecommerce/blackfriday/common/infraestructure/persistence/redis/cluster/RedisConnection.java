package org.ecommerce.blackfriday.common.infraestructure.persistence.redis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConnection {

    /**
     * Type safe representation of application.properties
     */
    @Autowired
    ClusterConfigurationProperties clusterProperties;

    public @Bean RedisConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(
                new RedisClusterConfiguration(clusterProperties.getNodes()));
    }
}
