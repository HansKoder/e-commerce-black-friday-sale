package org.ecommerce.blackfriday.e_commerce_black_friday_sale.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ProductAPI {



    @GetMapping("/")
    ResponseEntity<?> getProducts () {

    }

}
