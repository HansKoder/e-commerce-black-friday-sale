package org.ecommerce.blackfriday.cart.serv;

import org.ecommerce.blackfriday.cart.dto.DeleteItemDTO;
import org.ecommerce.blackfriday.cart.dto.SaveItemDTO;
import org.ecommerce.blackfriday.cart.model.Item;
import org.ecommerce.blackfriday.cart.model.ShoppingCart;
import org.ecommerce.blackfriday.cart.dto.ShoppingCartDTO;
import org.ecommerce.blackfriday.cart.exceptions.CartNotFoundException;
import org.ecommerce.blackfriday.cart.mapper.ItemMapper;
import org.ecommerce.blackfriday.cart.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    private ShoppingCartRedisService shoppingCartRedisService;

    @Override
    public ShoppingCartDTO createCart(SaveItemDTO itemDTO) {
        ShoppingCart domain = ShoppingCartMapper.fromSaveDTO(itemDTO);
        ShoppingCartDTO dto = ShoppingCartMapper.toDTO(domain);
        shoppingCartRedisService.saveCart(dto);

        return dto;
    }

    @Override
    public ShoppingCartDTO addItem(SaveItemDTO saveItemDTO) {
        ShoppingCartDTO cartDTO = getCartByCustomerId(saveItemDTO.getCustomerId());

        ShoppingCart cart = ShoppingCartMapper.fromDTO(cartDTO);
        cart.addItem(ItemMapper.fromSaveDTO(saveItemDTO));

        ShoppingCartDTO response = ShoppingCartMapper.toDTO(cart);
        shoppingCartRedisService.saveCart(response);

        return response;
    }

    @Override
    public ShoppingCartDTO updateItem(SaveItemDTO saveItemDTO) {
        ShoppingCartDTO cartDTO = getCartByCustomerId(saveItemDTO.getCustomerId());

        ShoppingCart cartEntity = ShoppingCartMapper.fromDTO(cartDTO);
        Optional<Item> itemOptional = cartEntity.getItems()
                .stream().filter(find -> find.getItemId().equals(saveItemDTO.getItemId()))
                .findFirst();

        /*
        if (itemOptional.isEmpty())
            throw new CartItemNotFoundException(saveItemDTO.getCustomerId(), saveItemDTO.getItemId().toString());

         */

        cartEntity.updateItem(ItemMapper.fromSaveDTO(saveItemDTO));

        ShoppingCartDTO response = ShoppingCartMapper.toDTO(cartEntity);
        shoppingCartRedisService.saveCart(response);

        return response;
    }

    @Override
    public void deleteItem(DeleteItemDTO deleteItemDTO) {
        ShoppingCartDTO cartDTO = getCartByCustomerId(deleteItemDTO.getCustomerId());
        ShoppingCart cart = ShoppingCartMapper.fromDTO(cartDTO);
        cart.removeItem(deleteItemDTO.getItemId());
        shoppingCartRedisService.saveCart(ShoppingCartMapper.toDTO(cart));
    }

    @Override
    public ShoppingCartDTO getCartByCustomerId(String customerId) {
        Optional<ShoppingCartDTO> cart = Optional
                .ofNullable(shoppingCartRedisService.getCart(customerId));

        if (cart.isEmpty()) throw new CartNotFoundException(customerId);

        return cart.get();
    }

}
