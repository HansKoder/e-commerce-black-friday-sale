package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.mapper;

import org.ecommerce.blackfriday.procurement.domain.model.valueobject.PurchaseStatus;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseStatusJPA;

import java.util.Objects;

public class PurchaseStatusMapper {

    public static PurchaseStatus toDomain (PurchaseStatusJPA jpa) {
        if (Objects.isNull(jpa)) return null;
        return PurchaseStatus.valueOf(jpa.name());
    }

    public static PurchaseStatusJPA toEntity (PurchaseStatus domain) {
        if (Objects.isNull(domain)) return null;
        return PurchaseStatusJPA.valueOf(domain.name());
    }

}
