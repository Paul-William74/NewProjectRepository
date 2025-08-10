package com.Ecommerce.demo.Controller.Admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {


    @GetMapping("/getProducts/{admin_id}")
    public ResponseEntity<?> getAllAdminProducts(@PathVariable final Long admin_id) {
        return null;
    }

    @GetMapping("/getMostSold/{admin_id}")
    public ResponseEntity<?> getMostSold(@PathVariable final Long admin_id) {
        return null;
    }

    @GetMapping("/getLeastSold/{admin_id}")
    public ResponseEntity<?> getLeastSold(@PathVariable final Long admin_id) {
        return null;
    }


    @GetMapping("/getAnalytics/{admin_id}")
    public ResponseEntity<?> getAdminAnalytics(@PathVariable final Long admin_id) {
        return null;
    }


    @GetMapping("/getOrders/{admin_id}")
    public ResponseEntity<?> getAdminOrders(@PathVariable final Long admin_id) {
        return null;
    }

}
