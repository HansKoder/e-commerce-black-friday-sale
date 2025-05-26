package org.ecommerce.pricing.ms_pricing.purchasehistory.infraestructure.persistence.jpa.mapper;

import org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.entity.PurchaseRecord;
import org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.valueobject.PurchaseRecordId;
import org.ecommerce.pricing.ms_pricing.purchasehistory.infraestructure.persistence.jpa.entity.PurchaseRecordEntity;

public class PurchaseRecordMapper {

    public static PurchaseRecord toDomain (PurchaseRecordEntity jpa) {
        return PurchaseRecord.rebuild(
                new PurchaseRecordId(jpa.getId()),
                jpa.getPurchaseId(),
                jpa.getProductId(),
                jpa.getReceivedAt(),
                jpa.getUnitPrice()
        );
    }

}
