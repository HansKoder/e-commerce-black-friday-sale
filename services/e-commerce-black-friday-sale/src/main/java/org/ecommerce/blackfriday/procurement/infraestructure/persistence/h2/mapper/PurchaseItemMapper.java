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
        return new PurchaseItemEntity(
                domain.getId().getValue(),
                domain.getProduct().getId().getValue(),
                domain.getProduct().getPrice().value().getAmount(),
                domain.getQuantity().getValue(),
                domain.getSubTotal().getAmount()
        );
    }

    private static Product productToDomain (PurchaseItemEntity entity) {
        return new Product.Builder()
                .withProductId(new ProductId(entity.getProductId()))
                .withPrice(new ProductPrice(new Money(entity.getCostPerUnit())))
                .build();
    }

    public static PurchaseItem toDomain (PurchaseItemEntity entity) {
        return new PurchaseItem(
                productToDomain(entity),
                new Quantity(entity.getQuantity()));
    }

}
