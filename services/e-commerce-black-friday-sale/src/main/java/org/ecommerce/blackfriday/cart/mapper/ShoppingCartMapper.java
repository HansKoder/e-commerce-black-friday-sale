package org.ecommerce.blackfriday.cart.mapper;

import org.ecommerce.blackfriday.cart.model.ShoppingCart;
import org.ecommerce.blackfriday.cart.dto.SaveItemDTO;
import org.ecommerce.blackfriday.cart.dto.ShoppingCartDTO;

public class ShoppingCartMapper {

    public static ShoppingCartDTO toDTO (ShoppingCart cart) {
        return ShoppingCartDTO.ShoppingCartDTOBuilder
                .aShoppingCartDTO()
                .withCustomerId(cart.getCustomerId())
                .withItems(cart.getItems()
                        .stream().map(ItemMapper::toDTO)
                        .toList())
                .withTotal(cart.getTotal())
                .build();
    }

    public static ShoppingCart fromDTO (ShoppingCartDTO dto) {
        ShoppingCart entity = new ShoppingCart();

        entity.setCustomerId(dto.getCustomerId());
        dto.getItems()
                .forEach(itemDTO -> entity.addItem(ItemMapper.fromDTO(itemDTO)));

        return entity;
    }

    public static ShoppingCart fromSaveDTO (SaveItemDTO dto) {
        ShoppingCart entity = new ShoppingCart();

        entity.setCustomerId(dto.getCustomerId());
        entity.addItem(ItemMapper.fromSaveDTO(dto));

        return entity;
    }

}
