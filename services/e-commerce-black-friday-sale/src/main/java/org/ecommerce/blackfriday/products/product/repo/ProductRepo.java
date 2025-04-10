package org.ecommerce.blackfriday.products.product.repo;

import org.ecommerce.blackfriday.products.product.ent.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
