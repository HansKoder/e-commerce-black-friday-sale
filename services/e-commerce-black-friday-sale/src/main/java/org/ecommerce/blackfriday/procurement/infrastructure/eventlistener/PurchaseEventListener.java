package org.ecommerce.blackfriday.procurement.infrastructure.eventlistener;

import org.ecommerce.blackfriday.procurement.domain.events.PurchaseStatusChangeEvent;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.adapter.PurchaseStatusHistoryRepo;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.entity.PurchaseStatusHistoryEntity;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class PurchaseEventListener {

    private final PurchaseStatusHistoryRepo purchaseStatusHistoryRepo;

    public PurchaseEventListener(PurchaseStatusHistoryRepo purchaseStatusHistoryRepo) {
        this.purchaseStatusHistoryRepo = purchaseStatusHistoryRepo;
    }

    @Async
    @EventListener
    public void handler (PurchaseStatusChangeEvent event) {
        PurchaseStatusHistoryEntity history = new PurchaseStatusHistoryEntity();

        history.setId(UUID.randomUUID());
        history.setPurchaseId(event.getPurchaseId());
        history.setNewStatus(event.getNewStatus());
        history.setDate(event.getChangedAt());

        history.setPrevStatus(event.getOldStatus());
        history.setComment(event.getComment());

        System.out.println("[USE_CASE] (history) param history {" + history + "}");
        purchaseStatusHistoryRepo.save(history);
    }
}
