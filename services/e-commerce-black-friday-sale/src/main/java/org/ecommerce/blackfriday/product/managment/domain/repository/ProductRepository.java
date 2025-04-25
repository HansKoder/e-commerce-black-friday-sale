package org.ecommerce.blackfriday.product.managment.domain.repository;

import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.query.ProductQuery;

import java.util.List;

public interface ProductRepository {
    void save (Product product);
    List<Product> findAll(ProductQuery query);
}
