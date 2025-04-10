package org.ecommerce.blackfriday.shopping.cart.serv;

import org.ecommerce.blackfriday.shopping.cart.dto.ShoppingCartDTO;

public interface ShoppingCartRedisService {

    void saveCart (ShoppingCartDTO cartDTO);
    ShoppingCartDTO getCart (String customerId);

}
