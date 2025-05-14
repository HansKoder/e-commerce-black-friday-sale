package org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.adapter;

import org.ecommerce.blackfriday.procurement.domain.model.entity.Purchase;
import org.ecommerce.blackfriday.procurement.domain.model.repository.PurchaseRepository;
import org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.mapper.PurchaseMapper;
import org.ecommerce.blackfriday.procurement.infraestructure.persistence.h2.springdata.SpringDataPurchaseRepository;
import org.springframework.stereotype.Repository;

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
}
