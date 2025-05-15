package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.mapper;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Provider;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.ProviderEmbeddable;

import java.util.Objects;

public class ProviderMapper {

    public static Provider toDomain (ProviderEmbeddable jpa) {
        if (Objects.isNull(jpa) ) return null;

        return Provider.ProviderBuilder.aProvider()
                .withName(jpa.getName())
                .withPhone(jpa.getPhone())
                .withEmail(jpa.getEmail())
                .build();
    }

    public static ProviderEmbeddable toJpa (Provider domain) {
        if (Objects.isNull(domain) ) return null;

        return ProviderEmbeddable.ProviderBuilder.aProvider()
                .withName(domain.getName())
                .withPhone(domain.getPhone())
                .withEmail(domain.getEmail())
                .build();
    }

}
