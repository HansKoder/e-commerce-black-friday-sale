package org.ecommerce.blackfriday.product.managment.infraestructure.persistence.adapter;

import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.repository.ProductRepository;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.mapper.ProductEntityMapper;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.springdata.SpringDataProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProductRepository implements ProductRepository {

    @Autowired
    SpringDataProductRepository springDataRepo;

    @Override
    public void save(Product product) {
        springDataRepo.save(ProductEntityMapper.toEntity(product));
    }

    @Override
    public List<Product> findAll() {
        return springDataRepo.findAll()
                .stream()
                .map(ProductEntityMapper::toDomain)
                .toList();
    }
}
