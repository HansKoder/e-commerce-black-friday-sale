package org.ecommerce.blackfriday.procurement.application.service;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePurchaseService {

    private final PurchaseRepository repository;

    public CreatePurchaseService(PurchaseRepository repository) {
        this.repository = repository;
    }

    public Purchase handler (Purchase domain) {
        repository.save(domain);
        return domain;
    }
}
