package org.ecommerce.blackfriday.e_commerce_black_friday_sale.mapper;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.domain.Item;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.ItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.SaveItemDTO;

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
