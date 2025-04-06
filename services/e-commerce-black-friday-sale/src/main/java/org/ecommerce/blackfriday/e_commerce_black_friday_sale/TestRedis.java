package org.ecommerce.blackfriday.e_commerce_black_friday_sale;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.sync.RedisCommands;

public class TestRedis {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://172.28.0.1:7001");
        RedisCommands<String, String> commands = client.connect().sync();
        String result = commands.ping();
        System.out.println("PING: " + result);
        client.shutdown();
    }

}
