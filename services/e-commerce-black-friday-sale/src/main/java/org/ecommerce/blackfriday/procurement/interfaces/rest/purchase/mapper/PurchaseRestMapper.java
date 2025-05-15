package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.mapper;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.entity.PurchaseItem;
import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseDate;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request.CreatePurchaseRequest;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response.GetPurchaseResponse;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response.PurchaseItemResponse;

import java.util.List;
import java.util.Objects;

public class PurchaseRestMapper {

    public static Purchase toDomain (CreatePurchaseRequest request) {
        if (Objects.isNull(request)) return null;

        List<PurchaseItem> items = request.items()
                .stream().map(PurchaseItemRestMapper::toDomain)
                .toList();

        return Purchase.create(
                ProviderRestMapper.toDomain(request.provider()),
                new PurchaseDate(request.date()),
                items
        );
    }

    public static GetPurchaseResponse toResponse (Purchase domain) {
        System.out.println("[MAPPER REST] (toResponse) param domain {  " + domain + "}" );
        if (Objects.isNull(domain)) return null;

        List<PurchaseItemResponse> items = domain.getItems()
                .stream().map(PurchaseItemRestMapper::toResponse)
                .toList();

        return new GetPurchaseResponse(
                domain.getId().getValue().toString(),
                domain.getPurchaseDate().value(),
                ProviderRestMapper.toResponse(domain.getProvider()),
                domain.getStatus().name(),
                items,
                domain.getTotal()
        );
    }

}
