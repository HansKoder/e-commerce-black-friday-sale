package org.ecommerce.blackfriday.product.managment.domain.model.repository;

import org.ecommerce.blackfriday.product.managment.domain.model.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.model.model.ProductQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    void save (Product product);
    List<Product> findAll(ProductQuery query);
    Optional<Product> findById (UUID productId);
}
