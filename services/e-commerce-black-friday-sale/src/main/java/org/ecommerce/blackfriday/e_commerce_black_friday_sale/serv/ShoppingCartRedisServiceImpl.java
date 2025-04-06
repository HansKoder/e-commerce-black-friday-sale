package org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShoppingCartRedisServiceImpl implements ShoppingCartRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveCart(ShoppingCartDTO cartDTO) {
        try {
            String json = objectMapper.writeValueAsString(cartDTO);
            redisTemplate.opsForValue().set("cart:" + cartDTO.getCustomerId(), json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShoppingCartDTO getCart(String customerId) {
        String json = redisTemplate.opsForValue().get("cart:" + customerId);
        try {
            return objectMapper.readValue(json, ShoppingCartDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
