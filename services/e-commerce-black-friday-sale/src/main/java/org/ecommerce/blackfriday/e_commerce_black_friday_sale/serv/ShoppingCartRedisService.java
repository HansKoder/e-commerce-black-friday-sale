package org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.ShoppingCartDTO;

public interface ShoppingCartRedisService {

    void saveCart (ShoppingCartDTO cartDTO);
    ShoppingCartDTO getCart (String customerId);

}
