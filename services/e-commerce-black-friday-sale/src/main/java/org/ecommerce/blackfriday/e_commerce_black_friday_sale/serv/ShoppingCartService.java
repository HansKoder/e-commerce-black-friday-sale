package org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.DeleteItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.SaveItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.ShoppingCartDTO;

public interface ShoppingCartService {

    ShoppingCartDTO createCart (SaveItemDTO itemDTO);
    ShoppingCartDTO addItem (SaveItemDTO itemDTO);
    ShoppingCartDTO updateItem (SaveItemDTO itemDTO);
    void deleteItem (DeleteItemDTO deleteItemDTO);
    ShoppingCartDTO getCartByCustomerId (String customerId);
}
