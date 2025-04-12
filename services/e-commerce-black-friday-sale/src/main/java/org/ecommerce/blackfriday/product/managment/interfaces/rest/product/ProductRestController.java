package org.ecommerce.blackfriday.product.managment.interfaces.rest.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Product-API version 2", description = "Controller to handler a CRUD")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v2/products")
public class ProductRestController {

    @Operation(
            summary = "Get Products",
            description = "Get a list of products without having any filter"
    )
    @GetMapping("/")
    ResponseEntity<?> getProducts () {
        return ResponseEntity.ok(productService.getProducts());
    }

}
