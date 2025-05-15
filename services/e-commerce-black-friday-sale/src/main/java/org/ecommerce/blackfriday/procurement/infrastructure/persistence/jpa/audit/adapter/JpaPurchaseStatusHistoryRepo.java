package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.adapter;

import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.entity.PurchaseStatusHistoryEntity;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.audit.springdata.SpringDataPurchaseStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaPurchaseStatusHistoryRepo implements PurchaseStatusHistoryRepo{

    private final SpringDataPurchaseStatusRepository repository;

    public JpaPurchaseStatusHistoryRepo(SpringDataPurchaseStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(PurchaseStatusHistoryEntity entity) {
        this.repository.save(entity);
    }

    @Override
    public List<PurchaseStatusHistoryEntity> findByPurchaseId(UUID id) {
        return repository.findByPurchaseId(id);
    }
}
