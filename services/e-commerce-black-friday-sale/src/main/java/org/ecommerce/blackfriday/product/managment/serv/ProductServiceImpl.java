package org.ecommerce.blackfriday.product.managment.serv;

import org.ecommerce.blackfriday.product.managment.ent.Product;
import org.ecommerce.blackfriday.product.managment.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo repo;

    @Override
    public List<Product> getProducts() {
        return repo.findAll();
    }

    @Override
    public Product saveProduct(Product entity) {
        return repo.save(entity);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return repo.findById(id);
    }
}
