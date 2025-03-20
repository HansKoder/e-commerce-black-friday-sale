package org.ecommerce.blackfriday.e_commerce_black_friday_sale.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
