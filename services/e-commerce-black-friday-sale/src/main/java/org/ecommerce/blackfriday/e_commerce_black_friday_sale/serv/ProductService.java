package org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv;

import org.ecommerce.blackfriday.e_commerce_black_friday_sale.ent.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProducts ();

    Product saveProduct (Product entity);

    Optional<Product> getProduct (Long id);

}
