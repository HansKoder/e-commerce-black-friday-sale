package org.ecommerce.blackfriday.products.product.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ecommerce.blackfriday.products.product.ent.Product;
import org.ecommerce.blackfriday.products.product.serv.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product-API", description = "Controller to handler a CRUD")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/products")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Get Products",
            description = "Get a list of products without having any filter"
    )
    @GetMapping("/")
    ResponseEntity<?> getProducts () {
        return ResponseEntity.ok(productService.getProducts());
    }

    @Operation(
            summary = "Add new Product",
            description = "Create new product in the DB"
    )
    @PostMapping("/")
    ResponseEntity<?> addProduct (@RequestBody Product entity) {
        return ResponseEntity.ok(productService.saveProduct(entity));
    }
}
