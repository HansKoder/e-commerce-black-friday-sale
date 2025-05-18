package org.ecommerce.blackfriday.procurement.application.service;

import org.ecommerce.blackfriday.procurement.domain.events.PurchaseStatusChangeEvent;
import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.entity.PurchaseItem;
import org.ecommerce.blackfriday.procurement.domain.model.repository.PurchaseRepository;
import org.ecommerce.blackfriday.procurement.infrastructure.PurchaseLogger;
import org.ecommerce.blackfriday.procurement.infrastructure.gateway.ProductACL;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseStatusJPA;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.exception.PurchaseItemInvalidException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreatePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ProductACL productACL;

    public CreatePurchaseService(
            PurchaseRepository repository,
            ApplicationEventPublisher eventPublisher,
            ProductACL productACL) {
        this.purchaseRepository = repository;
        this.eventPublisher = eventPublisher;
        this.productACL = productACL;
    }

    public Purchase handler (Purchase domain) {
        PurchaseLogger.info("[USE CASE] (create purchase) param domain {}", domain);
        if (Objects.isNull(domain))
            throw new IllegalArgumentException("Domain must be mandatory");

        List<String> ids = new ArrayList<>();
        for (PurchaseItem item: domain.getItems()) {
            UUID productId = item.getProduct().getId().getValue();
            if (!productACL.exist(productId))
                ids.add(productId.toString());
        }

        if (!ids.isEmpty()) {
            PurchaseLogger.error("[USE CASE] (create purchase) purchase has invalid products, params ids {}", ids);
            throw new PurchaseItemInvalidException(String.join(", ", ids));
        }

        purchaseRepository.save(domain);
        eventPublisher.publishEvent(new PurchaseStatusChangeEvent.Builder(domain.getId().getValue(), PurchaseStatusJPA.CREATE).build());
        return domain;
    }
}
