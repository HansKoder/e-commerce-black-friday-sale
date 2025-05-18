package org.ecommerce.blackfriday.product.managment.application.service;

import org.ecommerce.blackfriday.product.managment.domain.model.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.model.repository.ProductRepository;
import org.ecommerce.blackfriday.product.managment.domain.model.valueobject.ProductStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ExistProductService {

    private final ProductRepository productRepository;

    public ExistProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean exist (UUID productId) {
        Optional<Product> opt = productRepository.findById(productId);
        return opt.isPresent() && !opt.get().getStatus().equals(ProductStatus.INACTIVE);
    }

}
