package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.mapper;

import org.ecommerce.blackfriday.common.domain.model.entity.Product;
import org.ecommerce.blackfriday.common.domain.model.valueobject.Money;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductPrice;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request.PurchaseItemRequest;

import java.util.Objects;

public class ProductRestMapper {

    public static Product toDomain (PurchaseItemRequest request) {
        if (Objects.isNull(request)) return null;

        ProductId productId = new ProductId(request.productId());
        ProductPrice productPrice = new ProductPrice(new Money(request.costPerUnit()));
        return new Product.Builder()
                .withProductId(productId)
                .withPrice(productPrice)
                .build();
    }


}
