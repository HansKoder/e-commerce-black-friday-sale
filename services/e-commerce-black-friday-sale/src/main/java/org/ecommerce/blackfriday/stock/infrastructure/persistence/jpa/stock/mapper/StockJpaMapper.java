package org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.mapper;

import org.ecommerce.blackfriday.common.domain.model.valueobject.ProductId;
import org.ecommerce.blackfriday.stock.domain.model.entity.Stock;
import org.ecommerce.blackfriday.stock.domain.model.valueobject.StockId;
import org.ecommerce.blackfriday.stock.infrastructure.persistence.jpa.stock.entity.StockEntity;

public class StockJpaMapper {

    public static Stock toDomain (StockEntity entity) {
        System.out.println("[MAPPER JPA] (toDamin) entity { " + entity.toString() + "}");
        return Stock.rebuild(
                new StockId(entity.getId()),
                entity.getAmount(),
                new ProductId(entity.getProductId()));
    }

    public static StockEntity toEntity (Stock domain) {
        System.out.println("[MAPPER JPA] (toEntity) domain {" + domain.toString() + "}");

        StockEntity entity = new StockEntity();
        entity.setId(domain.getId().getValue());
        entity.setAmount(domain.getAmount());
        entity.setProductId(domain.getProductId().getValue());

        return entity;
    }

}
