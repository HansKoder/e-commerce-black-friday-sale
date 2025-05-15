package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.mapper;

import org.ecommerce.blackfriday.procurement.domain.model.entity.PurchaseItem;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.Quantity;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request.PurchaseItemRequest;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response.PurchaseItemResponse;

import java.util.Objects;

public class PurchaseItemRestMapper {

    public static PurchaseItem toDomain (PurchaseItemRequest request) {
        if (Objects.isNull(request)) return null;

        return PurchaseItem.create(
                ProductRestMapper.toDomain(request),
                new Quantity(request.quantity())
        );
    }

    public static PurchaseItemResponse toResponse (PurchaseItem domain ) {
        if (Objects.isNull(domain)) return null;

        return new PurchaseItemResponse(
                domain.getId().getValue().toString(),
                domain.getProduct().getId().getValue().toString(),
                domain.getProduct().getPrice().value().getAmount(),
                domain.getQuantity().getValue(),
                domain.getSubTotal().getAmount()
        );
    }

}
