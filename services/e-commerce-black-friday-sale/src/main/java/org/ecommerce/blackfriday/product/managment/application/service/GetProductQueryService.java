package org.ecommerce.blackfriday.product.managment.application.service;

import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.query.ProductQuery;
import org.ecommerce.blackfriday.product.managment.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductQueryService {

    private final ProductRepository productRepository;

    public GetProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> handler (ProductQuery query) {
        return this.productRepository.findAll(query);
    }
}
