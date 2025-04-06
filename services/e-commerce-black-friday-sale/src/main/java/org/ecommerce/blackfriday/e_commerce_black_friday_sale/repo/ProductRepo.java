package org.ecommerce.blackfriday.e_commerce_black_friday_sale.repo;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.ent.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
