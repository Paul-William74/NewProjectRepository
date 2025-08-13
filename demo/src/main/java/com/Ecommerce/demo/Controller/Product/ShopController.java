package com.Ecommerce.demo.Controller.Product;

import com.Ecommerce.demo.Service.Product.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/showcase-jewelleryTypes")
    public ResponseEntity<?> getJewelleriesByType() {
        return shopService.getAllJewelleryTypes();
    }


    @GetMapping("/showcase-allProducts")
    public ResponseEntity<?> getAllProducts() {
        return shopService.getAllShopProducts();
    }
}
