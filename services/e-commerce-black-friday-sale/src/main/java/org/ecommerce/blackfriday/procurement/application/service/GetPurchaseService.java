package org.ecommerce.blackfriday.procurement.application.service;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.repository.PurchaseRepository;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.exception.PurchaseNotFoundRestException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class GetPurchaseService {

    private final PurchaseRepository purchaseRepository;

    public GetPurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase handler (String uuid) {
        return purchaseRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new PurchaseNotFoundRestException(uuid));
    }

}
