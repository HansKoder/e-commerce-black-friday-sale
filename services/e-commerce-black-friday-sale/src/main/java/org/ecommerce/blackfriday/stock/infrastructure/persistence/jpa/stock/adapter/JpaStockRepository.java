package org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.adapter;

import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;
import org.ecommerce.blackfriday.stock.domain.model.repository.StockRepository;
import org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.entity.StockEntity;
import org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.mapper.StockJpaMapper;
import org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.springdata.SpringDataStockRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaStockRepository implements StockRepository {
    private final SpringDataStockRepository springDataStockRepository;

    public JpaStockRepository(SpringDataStockRepository springDataStockRepository) {
        this.springDataStockRepository = springDataStockRepository;
    }

    @Override
    public Stock save(Stock domain) {
        System.out.println("[JPA REPOSITORY] domain { " + domain + "}");
        StockEntity entity = springDataStockRepository.save(StockJpaMapper.toEntity(domain));
        return StockJpaMapper.toDomain(entity);
    }

    @Override
    public Optional<Stock> findByProductId(UUID productId) {
        System.out.println("[JPA REPOSITORY] domain { " + productId + "}");

        System.out.println("[LIST ALL STOCKS..]");
        Iterable<StockEntity> list = springDataStockRepository.findAll();
        list.forEach(System.out::println);

        return springDataStockRepository.findByProductId(productId)
                .map(StockJpaMapper::toDomain);
    }
}
