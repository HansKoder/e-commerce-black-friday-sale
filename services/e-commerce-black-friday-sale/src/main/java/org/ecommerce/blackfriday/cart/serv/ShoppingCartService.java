package org.ecommerce.blackfriday.cart.serv;

import org.ecommerce.blackfriday.cart.dto.DeleteItemDTO;
import org.ecommerce.blackfriday.cart.dto.SaveItemDTO;
import org.ecommerce.blackfriday.cart.dto.ShoppingCartDTO;

public interface ShoppingCartService {

    ShoppingCartDTO createCart (SaveItemDTO itemDTO);
    ShoppingCartDTO addItem (SaveItemDTO itemDTO);
    ShoppingCartDTO updateItem (SaveItemDTO itemDTO);
    void deleteItem (DeleteItemDTO deleteItemDTO);
    ShoppingCartDTO getCartByCustomerId (String customerId);
}
