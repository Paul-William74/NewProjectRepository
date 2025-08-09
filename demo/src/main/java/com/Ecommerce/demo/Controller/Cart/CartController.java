package com.Ecommerce.demo.Controller.Cart;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {



    @GetMapping("/checkout-products/{cart_id}/{customer_id}")
    public ResponseEntity<?> getCheckoutProducts(
            @PathVariable final Long cart_id,
            @PathVariable final Long customer_id) {
        return null;
    }

}
