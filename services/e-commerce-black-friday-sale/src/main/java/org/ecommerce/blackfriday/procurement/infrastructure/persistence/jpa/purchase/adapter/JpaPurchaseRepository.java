package org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.adapter;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.repository.PurchaseRepository;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.mapper.PurchaseMapper;
import org.ecommerce.blackfriday.procurement.infrastructure.persistence.jpa.purchase.springdata.SpringDataPurchaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaPurchaseRepository implements PurchaseRepository {

    private final SpringDataPurchaseRepository springDataRepository;

    public JpaPurchaseRepository(SpringDataPurchaseRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public void save(Purchase domain) {
        System.out.println("[REPOSITORY] (save) param Purchase {" + domain + "}");
        springDataRepository.save(PurchaseMapper.toEntity(domain));
    }

    @Override
    public Optional<Purchase> findById(UUID uuid) {
        return springDataRepository.findById(uuid).map(PurchaseMapper::toDomain);
    }
}
