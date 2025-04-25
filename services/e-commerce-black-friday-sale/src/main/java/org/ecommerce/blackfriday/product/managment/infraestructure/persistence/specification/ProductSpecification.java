package org.ecommerce.blackfriday.product.managment.infraestructure.persistence.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.ecommerce.blackfriday.product.managment.domain.query.ProductQuery;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductSpecification {

    public static Specification<ProductEntity> withQuery(ProductQuery productQuery) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            addLikeIfPresent(cb, root.get("name"), productQuery.getProductName(), predicates);
            addLikeIfPresent(cb, root.get("description"), productQuery.getProductDescription(), predicates);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void addLikeIfPresent(CriteriaBuilder cb, Path<String> path, String value, List<Predicate> predicates) {
        Optional.ofNullable(value)
                .map(v -> cb.like(cb.lower(path), "%" + v.toLowerCase() + "%"))
                .ifPresent(predicates::add);
    }

}
