package org.ecommerce.blackfriday.products.product.serv;

import org.ecommerce.blackfriday.products.product.ent.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProducts ();

    Product saveProduct (Product entity);

    Optional<Product> getProduct (Long id);

}
