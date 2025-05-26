package org.ecommerce.pricing.ms_pricing.purchasehistory.infraestructure.persistence.jpa.adapter;

import org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.entity.PurchaseRecord;
import org.ecommerce.pricing.ms_pricing.purchasehistory.domain.model.repository.PurchaseRecordRepository;
import org.ecommerce.pricing.ms_pricing.purchasehistory.infraestructure.persistence.jpa.mapper.PurchaseRecordMapper;
import org.ecommerce.pricing.ms_pricing.purchasehistory.infraestructure.persistence.jpa.springdata.SpringDataPurchaseHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaPurchaseRecordRepository implements PurchaseRecordRepository {

    private final SpringDataPurchaseHistoryRepository repository;

    public JpaPurchaseRecordRepository(SpringDataPurchaseHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PurchaseRecord> getByRangeDate(UUID productId, LocalDateTime from, LocalDateTime to) {
        return repository.findByProductIdAndReceivedAtBetween(productId, from, to)
                .stream().map(PurchaseRecordMapper::toDomain)
                .toList();
    }

    @Override
    public Page<PurchaseRecord> getByLimit(UUID productId, Pageable page) {
        return null;
    }

    @Override
    public Optional<PurchaseRecord> getLast(UUID productId) {
        return Optional.empty();
    }
}
