package org.ecommerce.blackfriday.product.managment.infraestructure.persistence.adapter;

import org.ecommerce.blackfriday.product.managment.domain.model.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.model.model.ProductQuery;
import org.ecommerce.blackfriday.product.managment.domain.model.repository.ProductRepository;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.entity.ProductEntity;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.mapper.ProductEntityMapper;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.specification.ProductSpecification;
import org.ecommerce.blackfriday.product.managment.infraestructure.persistence.springdata.SpringDataProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public List<Product> findAll(ProductQuery query) {
        Specification<ProductEntity> spec = ProductSpecification.withQuery(query);

        Pageable pageable = PageRequest.of(
                query.getPagination().page(),
                query.getPagination().size()
        );

        return springDataRepo
                .findAll(spec, pageable)
                .stream()
                .map(ProductEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return springDataRepo.findById(productId)
                .map(ProductEntityMapper::toDomain);
    }
}
