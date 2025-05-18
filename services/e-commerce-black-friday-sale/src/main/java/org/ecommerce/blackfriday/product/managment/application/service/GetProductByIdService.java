package org.ecommerce.blackfriday.product.managment.application.service;

import org.ecommerce.blackfriday.product.managment.domain.model.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.model.repository.ProductRepository;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.exception.ProductNotFoundRestException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetProductByIdService {

    private final ProductRepository productRepository;

    public GetProductByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product handler (UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundRestException(productId.toString()));
    }

}
