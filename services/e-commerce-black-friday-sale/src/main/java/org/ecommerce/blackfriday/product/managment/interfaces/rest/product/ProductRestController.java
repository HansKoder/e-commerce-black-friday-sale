package org.ecommerce.blackfriday.product.managment.interfaces.rest.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.ecommerce.blackfriday.common.domain.model.query.Pagination;
import org.ecommerce.blackfriday.product.managment.application.service.CreateProductService;
import org.ecommerce.blackfriday.product.managment.application.service.GetProductQueryService;
import org.ecommerce.blackfriday.product.managment.domain.entity.Product;
import org.ecommerce.blackfriday.product.managment.domain.query.ProductQuery;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.CreateProductRequest;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.dto.GetProductResponse;
import org.ecommerce.blackfriday.product.managment.interfaces.rest.product.mapper.ProductDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product-API version 2", description = "Product Management handler product")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductRestController {

    private final CreateProductService createProductService;
    private final GetProductQueryService getProductQueryService;

    public ProductRestController(
            CreateProductService createProductService,
            GetProductQueryService getProductQueryService
    ) {
        this.createProductService = createProductService;
        this.getProductQueryService = getProductQueryService;
    }

    @Operation(
            summary = "Get Products",
            description = "Get a list of products without having any filter"
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

}
