package org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setKey (String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    public String getKey (String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
