package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.mapper;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.entity.PurchaseItem;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseDate;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseId;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseEntity;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseItemEntity;

import java.util.List;

public class PurchaseMapper {
    public static Purchase toDomain (PurchaseEntity entity) {
        System.out.println("[MAPPER SERVICE] (toDomain) entity {" + entity + "}");
        List<PurchaseItem> items = entity.getItems()
                .stream().map(PurchaseItemMapper::toDomain)
                .toList();

        return Purchase.rebuild(
                new PurchaseId(entity.getId()),
                ProviderMapper.toDomain(entity.getProvider()),
                PurchaseStatusMapper.toDomain(entity.getStatus()),
                new PurchaseDate(entity.getDate()),
                items
        );
    }

    public static PurchaseEntity toEntity (Purchase domain) {
        List<PurchaseItemEntity> items = domain.getItems()
                .stream().map(PurchaseItemMapper::toEntity)
                .toList();

        PurchaseEntity entity = new PurchaseEntity();

        entity.setId(domain.getId().getValue());
        entity.setTotal(domain.getTotal());
        entity.setStatus(PurchaseStatusMapper.toEntity(domain.getStatus()));
        entity.setItems(items);
        entity.setProvider(ProviderMapper.toJpa(domain.getProvider()));

        return entity;
    }
}
