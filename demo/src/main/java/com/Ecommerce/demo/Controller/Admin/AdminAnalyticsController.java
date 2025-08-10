package com.Ecommerce.demo.Controller.Admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminAnalyticsController {


    @GetMapping("/getProducts")
    public ResponseEntity<?> getAllAdminProducts() {
        return null;
    }

    @GetMapping("/getMostSold")
    public ResponseEntity<?> getMostSold() {
        return null;
    }

    @GetMapping("/getLeastSold/")
    public ResponseEntity<?> getLeastSold() {
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
