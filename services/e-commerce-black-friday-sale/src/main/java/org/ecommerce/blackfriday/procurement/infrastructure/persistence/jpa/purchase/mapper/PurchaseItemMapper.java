package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.mapper;

import org.ecommerce.blackfriday.procurement.domain.model.entity.PurchaseItem;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseItemId;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.Quantity;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseItemEntity;

public class PurchaseItemMapper {

    public static PurchaseItemEntity toEntity (PurchaseItem domain) {
        PurchaseItemEntity entity = new PurchaseItemEntity();

        entity.setId(domain.getId().getValue());
        entity.setProductId(domain.getProduct().getId().getValue());
        entity.setCostPerUnit(domain.getProduct().getPrice().value().getAmount());
        entity.setQuantity(domain.getQuantity().getValue());
        entity.setSubtotal(domain.getSubTotal().getAmount());

        return entity;
    }

    public static PurchaseItem toDomain (PurchaseItemEntity entity) {
        return PurchaseItem.recreate(
                new PurchaseItemId(entity.getId()),
                ProductMapper.toDomain(entity),
                new Quantity(entity.getQuantity()));
    }

}
