package org.ecommerce.blackfriday.product.managment.interfaces.rest.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.ecommerce.blackfriday.common.domain.model.query.Pagination;
import org.ecommerce.blackfriday.product.managment.application.service.CreateProductService;
import org.ecommerce.blackfriday.product.managment.application.service.GetProductByIdService;
import org.ecommerce.blackfriday.product.managment.application.service.GetProductQueryService;
import org.ecommerce.blackfriday.product.managment.domain.model.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.model.model.ProductQuery;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.CreateProductRequest;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.GetProductResponse;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.mapper.ProductDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Management Product, Version V1", description = "Add new product and get Products with pagination and filters")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductRestController {

    private final CreateProductService createProductService;
    private final GetProductQueryService getProductQueryService;
    private final GetProductByIdService getProductByIdService;

    public ProductRestController(
            CreateProductService createProductService,
            GetProductQueryService getProductQueryService, GetProductByIdService getProductByIdService
    ) {
        this.createProductService = createProductService;
        this.getProductQueryService = getProductQueryService;
        this.getProductByIdService = getProductByIdService;
    }

    @Operation(
            summary = "Get Products",
            description = "Get a list of products with pagination and filters (name, description)"
    )
    @GetMapping("/")
    ResponseEntity<List<GetProductResponse>> getProducts (
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size
            ) {

        ProductQuery query = ProductQuery.ProductQueryBuilder
                .aProductQuery()
                .withProductName(name)
                .withProductDescription(description)
                .withPagination(new Pagination(page, size))
                .build();

        return ResponseEntity.ok(getProductQueryService
                .handler(query)
                .stream()
                .map(ProductDTOMapper::toDto)
                .toList());
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

    @GetMapping("/{productId}")
    ResponseEntity<GetProductResponse> getProductById (@PathVariable("productId") String productId) {
        Product domain = getProductByIdService.handler(UUID.fromString(productId));
        return ResponseEntity.ok(ProductDTOMapper.toDto(domain));
    }

}
