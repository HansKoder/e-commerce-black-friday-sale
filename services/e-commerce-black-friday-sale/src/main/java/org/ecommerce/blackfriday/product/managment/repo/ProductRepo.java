package org.ecommerce.blackfriday.product.managment.repo;

import org.ecommerce.blackfriday.product.managment.ent.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
