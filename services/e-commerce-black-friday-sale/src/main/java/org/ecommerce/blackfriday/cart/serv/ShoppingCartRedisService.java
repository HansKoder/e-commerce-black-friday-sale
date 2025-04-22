package org.ecommerce.blackfriday.cart.serv;

import org.ecommerce.blackfriday.cart.dto.ShoppingCartDTO;

public interface ShoppingCartRedisService {

    void saveCart (ShoppingCartDTO cartDTO);
    ShoppingCartDTO getCart (String customerId);

}
