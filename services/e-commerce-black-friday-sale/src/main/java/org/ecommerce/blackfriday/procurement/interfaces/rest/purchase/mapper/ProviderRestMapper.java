package org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.mapper;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Provider;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.request.ProviderRequest;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.dto.response.ProviderResponse;

import java.util.Objects;

public class ProviderRestMapper {
    public static Provider toDomain(ProviderRequest request) {
        if (Objects.isNull(request)) return null;

        return Provider.ProviderBuilder.aProvider()
                .withName(request.getName())
                .withPhone(request.getPhone())
                .withEmail(request.getEmail())
                .build();
    }

    public static ProviderResponse toResponse (Provider domain) {
        if (Objects.isNull(domain)) return null;

        return ProviderResponse.Builder.aProvider()
                .withName(domain.getName())
                .withEmail(domain.getEmail())
                .withPhone(domain.getPhone())
                .build();
    }
}
