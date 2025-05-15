package org.ecommerce.blackfriday.procurement.application.service;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.repository.PurchaseRepository;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.adapter.PurchaseStatusHistoryRepo;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.entity.PurchaseStatusHistoryEntity;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.entity.PurchaseStatusJPA;
import org.ecommerce.blackfriday.procurement.interfaces.rest.purchase.exception.PurchaseNotFoundRestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReceivedPurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseStatusHistoryRepo purchaseStatusHistoryRepo;

    public ReceivedPurchaseService(PurchaseRepository purchaseRepository, PurchaseStatusHistoryRepo purchaseStatusHistoryRepo) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseStatusHistoryRepo = purchaseStatusHistoryRepo;
    }

    private void history (UUID purchaseId, String comment) {
        PurchaseStatusHistoryEntity history = new PurchaseStatusHistoryEntity();

        history.setId(UUID.randomUUID());
        history.setPurchaseId(purchaseId);
        history.setNewStatus(PurchaseStatusJPA.CREATE);
        history.setDate(LocalDateTime.now());
        history.setComment(comment);

        purchaseStatusHistoryRepo.save(history);
        System.out.println("[USE_CASE] (history) new, param history {" + history + "}");
    }


    public Purchase handler (UUID purchaseId, String comment) {
        Purchase domain = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundRestException(purchaseId.toString()));

        System.out.println("[USE_CASE] (handler) Received Purchase, param Purchase {" + domain + "}");

        domain.received();
        purchaseRepository.save(domain);

        history(purchaseId, comment);

        return domain;
    }

}
