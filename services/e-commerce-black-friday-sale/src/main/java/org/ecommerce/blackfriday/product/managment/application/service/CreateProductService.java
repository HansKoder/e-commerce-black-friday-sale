package org.ecommerce.blackfriday.product.managment.application.service;

import org.ecommerce.blackfriday.product.managment.domain.model.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.model.repository.ProductRepository;
import org.ecommerce.blackfriday.product.managment.domain.model.valueobject.ProductStatus;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product handler (Product product) {
        product.setStatus(ProductStatus.DRAFT);
        product.validateInitialProduct();
        productRepository.save(product);
        return product;
    }
}
