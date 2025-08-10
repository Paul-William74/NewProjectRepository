package com.Ecommerce.demo.Controller.Admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminCraudController {


    @DeleteMapping("/removeProduct/{product_id}")
    public ResponseEntity<?> removeProduct(@PathVariable final Long product_id) {
        return null;
    }


    @DeleteMapping("/removeProductSize/{size_id}")
    public ResponseEntity<?> removeSizeWithOfAMaterial(
            @RequestParam("material") final String material) {
        return null;
    }

    @DeleteMapping("/removeProductImage/{product_image_id}")
    public ResponseEntity<?> removeProductImage(final Long product_image_id) {
        return null;
    }


}
