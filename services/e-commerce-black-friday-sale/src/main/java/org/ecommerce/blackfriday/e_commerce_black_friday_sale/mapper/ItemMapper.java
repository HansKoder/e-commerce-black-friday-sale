package org.ecommerce.blackfriday.e_commerce_black_friday_sale.mapper;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.domain.DetailShoppingCart;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.ItemDTO;

public class ItemMapper {

    public static ItemDTO toDTO (DetailShoppingCart item) {
        return ItemDTO.ItemDTOBuilder.anItemDTO()
                .withItemId(item.getItemId())
                .withCant(item.getCant())
                .withAmount(item.getAmount())
                .withPrice(item.getPrice())
                .build();
    }

    public static DetailShoppingCart fromDTO (ItemDTO dto) {
        DetailShoppingCart entity = new DetailShoppingCart();
        entity.setItemId(dto.getItemId());
        entity.setCant(dto.getCant());
        entity.setPrice(dto.getPrice());

        return entity;
    }

}
