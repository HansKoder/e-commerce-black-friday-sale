package org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.mapper;

import org.ecommerce.blackfriday.common.domain.model.entity.Product;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductPrice;
import org.ecommerce.blackfriday.procurement.domain.model.entity.PurchaseItem;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.Quantity;
import org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.entity.PurchaseItemEntity;

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

    private static Product productToDomain (PurchaseItemEntity entity) {
        return new Product.Builder()
                .withProductId(new ProductId(entity.getProductId()))
                .withPrice(new ProductPrice(new Money(entity.getCostPerUnit())))
                .build();
    }

    public static PurchaseItem toDomain (PurchaseItemEntity entity) {
        return PurchaseItem.create(
                productToDomain(entity),
                new Quantity(entity.getQuantity()));
    }

}
