package org.ecommerce.blackfriday.e_commerce_black_friday_sale.api;

import jakarta.validation.Valid;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.DeleteItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.dto.SaveItemDTO;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.models.ShoppingCart;
import org.ecommerce.blackfriday.e_commerce_black_friday_sale.serv.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/shopping-cart")
@Validated
public class ShoppingCartAPI {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add-item")
    ResponseEntity<?> addItem (@Valid @RequestBody SaveItemDTO itemDTO) {
        return ResponseEntity.ok(shoppingCartService.addItem(itemDTO));
    }

    @PutMapping("/update-item")
    ResponseEntity<?> updateItem (@Valid @RequestBody SaveItemDTO itemDTO) throws Exception {
        return ResponseEntity.ok(shoppingCartService.updateItem(itemDTO));
    }

    @DeleteMapping("/delete-item")
    ResponseEntity<?> removeItem (@Valid @RequestBody DeleteItemDTO itemDTO) throws Exception {
        shoppingCartService.deleteItem(itemDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-cart/{customerId}")
    ResponseEntity<?> getCart (@PathVariable("customerId") String customerId) {
        Optional<ShoppingCart> cart = shoppingCartService.getCartByCustomerId(customerId);
        if (cart.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cart.get());
    }

    @GetMapping("/find-all")
    ResponseEntity<?> getAll () {
        return ResponseEntity.ok(shoppingCartService.getList());
    }

}
