package org.ecommerce.blackfriday.procurement.application.service;

import org.ecommerce.blackfriday.procurement.domain.events.PurchaseStatusChangeEvent;
import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.repository.PurchaseRepository;
import org.ecommerce.blackfriday.procurement.infrastructure.PurchaseLogger;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseStatusJPA;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.exception.PurchaseNotFoundRestException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CanceledPurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CanceledPurchaseService(
            PurchaseRepository purchaseRepository,
            ApplicationEventPublisher eventPublisher) {
        this.purchaseRepository = purchaseRepository;
        this.eventPublisher = eventPublisher;
    }

    public Purchase handler (UUID purchaseId, String comment) {
        PurchaseLogger.info("[USE CASE] (cancel purchase) params purchaseId {}, comment {}", purchaseId, comment);

        Purchase domain = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundRestException(purchaseId.toString()));

        domain.canceled();
        purchaseRepository.save(domain);

        eventPublisher.publishEvent(new PurchaseStatusChangeEvent.Builder(purchaseId, PurchaseStatusJPA.CANCELED)
                .comment(comment)
                .oldStatus(PurchaseStatusJPA.CREATE)
                .build());

        return domain;
    }
}
