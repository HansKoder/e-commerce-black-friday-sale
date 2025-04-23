package org.ecommerce.blackfriday.product.managment.interfaces.rest.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.ecommerce.blackfriday.product.managment.application.service.CreateProductService;
import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.CreateProductRequest;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.GetProductResponse;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.mapper.ProductDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(name = "Product-API version 2", description = "Product Management handler product")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductRestController {

    private final CreateProductService createProductService;

    public ProductRestController(CreateProductService createProductService) {
        this.createProductService = createProductService;
    }

    @Operation(
            summary = "Get Products",
            description = "Get a list of products without having any filter"
    )
    @GetMapping("/")
    ResponseEntity<List<GetProductResponse>> getProducts () {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Operation(
            summary = "Add new Product",
            description = "Create new product in the DB"
    )
    @PostMapping("/")
    ResponseEntity<GetProductResponse> addProduct (@Valid @RequestBody CreateProductRequest request) {
        Product domain = createProductService.handler(ProductDTOMapper.toCreateProductDomain(request));
        return ResponseEntity.ok(ProductDTOMapper.toDto(domain));
    }

}
