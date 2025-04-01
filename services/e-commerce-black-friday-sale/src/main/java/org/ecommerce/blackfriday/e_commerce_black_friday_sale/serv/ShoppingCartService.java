package org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.DeleteItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.SaveItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.models.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    ShoppingCart addItem (SaveItemDTO itemDTO);
    ShoppingCart updateItem (SaveItemDTO itemDTO) throws Exception;
    void deleteItem (DeleteItemDTO deleteItemDTO) throws Exception;
    Optional<ShoppingCart> getCartByCustomerId (String customerId);
    List<ShoppingCart> getList ();
}
