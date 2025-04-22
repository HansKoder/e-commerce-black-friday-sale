package org.ecommerce.blackfriday.product.managment.serv;

import org.ecommerce.blackfriday.product.managment.ent.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProducts ();

    Product saveProduct (Product entity);

    Optional<Product> getProduct (Long id);

}
