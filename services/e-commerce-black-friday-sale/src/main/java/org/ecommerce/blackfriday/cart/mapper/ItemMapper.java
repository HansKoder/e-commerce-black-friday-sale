package org.ecommerce.blackfriday.cart.mapper;

import org.ecommerce.blackfriday.cart.model.Item;
import org.ecommerce.blackfriday.cart.dto.ItemDTO;
import org.ecommerce.blackfriday.cart.dto.SaveItemDTO;

public class ItemMapper {

    public static ItemDTO toDTO (Item item) {
        return ItemDTO.ItemDTOBuilder.anItemDTO()
                .withItemId(item.getItemId())
                .withCant(item.getCant())
                .withAmount(item.getAmount())
                .withPrice(item.getPrice())
                .build();
    }

    public static Item fromDTO (ItemDTO dto) {
        Item domain = new Item();
        domain.setItemId(dto.getItemId());
        domain.setCant(dto.getCant());
        domain.setPrice(dto.getPrice());

        return domain;
    }

    public static Item fromSaveDTO (SaveItemDTO dto) {
        Item domain = new Item();
        domain.setItemId(dto.getItemId());
        domain.setCant(dto.getCant());
        domain.setPrice(dto.getPrice());

        return domain;
    }

}
