package org.ecommerce.blackfriday.e_commerce_black_friday_sale.mapper;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.domain.ShoppingCart;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.SaveItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.ShoppingCartDTO;

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
