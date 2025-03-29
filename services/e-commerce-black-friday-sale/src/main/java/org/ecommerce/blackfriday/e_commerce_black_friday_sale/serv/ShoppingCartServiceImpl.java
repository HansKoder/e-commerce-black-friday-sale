package org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.DeleteItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.SaveItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.models.DetailShoppingCart;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.models.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    private final List<ShoppingCart> cartList = new ArrayList<>();

    @Override
    public ShoppingCart addItem(SaveItemDTO itemDTO) {
        ShoppingCart cart = cartList.stream()
                .filter(data -> data.getCustomerId().equals(itemDTO.getCustomerId()))
                .findFirst()
                .orElseGet(() -> createShoppingCart(itemDTO));

        cart.addItem(buildItem(itemDTO));

        return cart;
    }

    private DetailShoppingCart buildItem (SaveItemDTO itemDTO) {
        DetailShoppingCart item = new DetailShoppingCart();

        item.setItemId(itemDTO.getItemId());
        item.setPrice(itemDTO.getPrice());
        item.setCant(itemDTO.getCant());

        return item;
    }

    private ShoppingCart buildShoppingCart (SaveItemDTO itemDTO) {
        ShoppingCart cart = new ShoppingCart();
        cart.setCustomerId(itemDTO.getCustomerId());

        return cart;
    }

    private ShoppingCart createShoppingCart (SaveItemDTO itemDTO) {
        ShoppingCart cart = buildShoppingCart(itemDTO);
        cartList.add(cart);
        return cart;
    }

    @Override
    public ShoppingCart updateItem(SaveItemDTO itemDTO) throws Exception{
        ShoppingCart cart = cartList.stream()
                .filter(data -> data.getCustomerId().equals(itemDTO.getCustomerId()))
                .findFirst()
                .orElseThrow(Exception::new);

        cart.updateItem(buildItem(itemDTO));

        return cart;
    }

    @Override
    public void deleteItem(DeleteItemDTO deleteItemDTO) throws Exception {
        ShoppingCart cart = cartList.stream()
                .filter(data -> data.getCustomerId().equals(deleteItemDTO.getCustomerId()))
                .findFirst()
                .orElseThrow(Exception::new);

        cart.removeItem(deleteItemDTO.getItemId());
    }

    @Override
    public Optional<ShoppingCart> getCartByCustomerId(String customerId) {
        return cartList.stream()
                .filter(cart -> cart.getCustomerId().equals(customerId))
                .findFirst();
    }
}
