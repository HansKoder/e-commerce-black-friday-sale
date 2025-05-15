package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.mapper;

import org.ecommerce.blackfriday.common.domain.model.entity.Product;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductPrice;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseItemEntity;

public class ProductMapper {

    public static Product toDomain (PurchaseItemEntity entity) {
        return new Product.Builder()
                .withProductId(new ProductId(entity.getProductId()))
                .withPrice(new ProductPrice(new Money(entity.getCostPerUnit())))
                .build();
    }

}
