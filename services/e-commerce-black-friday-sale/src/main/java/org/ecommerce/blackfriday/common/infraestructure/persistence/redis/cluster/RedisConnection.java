package org.ecommerce.blackfriday.common.infraestructure.persistence.redis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
public class RedisConnection {

    /**
     * Type safe representation of application.properties
     */
    @Autowired
    ClusterConfigurationProperties clusterProperties;

    public @Bean RedisConnectionFactory connectionFactory() {
        // Lettuce Client Timeout Settings
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(2000))         // time for commands
                .shutdownTimeout(Duration.ofMillis(100))         // time for closeling operations
                .build();

        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(clusterProperties.getNodes());

        return new LettuceConnectionFactory(clusterConfig, clientConfig);
    }
}
