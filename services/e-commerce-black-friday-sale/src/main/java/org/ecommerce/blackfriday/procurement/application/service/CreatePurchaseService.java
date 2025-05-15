package org.ecommerce.blackfriday.procurement.application.service;

import org.ecommerce.blackfriday.procurement.domain.events.PurchaseStatusChangeEvent;
import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.repository.PurchaseRepository;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseStatusJPA;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreatePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CreatePurchaseService(PurchaseRepository repository, ApplicationEventPublisher eventPublisher) {
        this.purchaseRepository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Purchase handler (Purchase domain) {
        if (Objects.isNull(domain)) throw new IllegalArgumentException("Domain must be mandatory");

        System.out.println("[USE_CASE] (handler) Create Purchase, param Purchase {" + domain + "}");
        purchaseRepository.save(domain);

        eventPublisher.publishEvent(new PurchaseStatusChangeEvent.Builder(domain.getId().getValue(), PurchaseStatusJPA.CREATE).build());

        return domain;
    }
}
