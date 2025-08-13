package com.Ecommerce.demo.Controller.Admin;

import com.Ecommerce.demo.Service.User.Admin.AdminCraudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminCraudController {

    private final AdminCraudService adminCraudService;

    @PutMapping("/removeProduct/{product_id}")
    public ResponseEntity<?> removeProduct(@PathVariable final Long product_id) {
        return adminCraudService.deleteProduct(product_id);
    }



    @PutMapping("/removeProductImage/{product_image_id}")
    public ResponseEntity<?> removeProductImage(final Long product_image_id) {
        return null ;
    }

}
