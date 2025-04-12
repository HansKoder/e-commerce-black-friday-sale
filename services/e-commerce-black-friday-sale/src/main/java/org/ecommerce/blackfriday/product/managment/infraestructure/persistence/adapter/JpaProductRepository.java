package org.ecommerce.blackfriday.product.managment.infraestructure.persistence.adapter;

import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.repository.ProductRepository;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.entity.ProductEntity;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.mapper.ProductEntityMapper;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.springdata.SpringDataProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProductRepository implements ProductRepository {

    private final SpringDataProductRepository springDataRepo;

    public JpaProductRepository(SpringDataProductRepository springDataRepo) {
        this.springDataRepo = springDataRepo;
    }

    @Override
    public void save(Product product) {
        ProductEntity entity = springDataRepo.save(ProductEntityMapper.toEntity(product));
        System.out.println(entity);
    }

    @Override
    public List<Product> findAll() {
        return springDataRepo.findAll()
                .stream()
                .map(ProductEntityMapper::toDomain)
                .toList();
    }
}
