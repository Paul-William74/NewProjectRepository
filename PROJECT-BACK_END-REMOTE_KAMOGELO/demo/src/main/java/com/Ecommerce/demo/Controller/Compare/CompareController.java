package com.Ecommerce.demo.Controller.Compare;

import com.Ecommerce.demo.Service.CompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comparisons")
public class CompareController {

    @Autowired private CompareService compareService;

    @PostMapping("/addCompare/{customer_id}/{product_id}")
    public ResponseEntity<?> addCompare(
            @PathVariable final Long customer_id,
            @PathVariable final Long product_id_) {
        return this.compareService.addToCompare(customer_id, product_id_);
    }


    @DeleteMapping("/removeCompare/{customer_id}/{product_id}")
    public ResponseEntity<?> removeCompare(
            @PathVariable final Long customer_id,
            @PathVariable final Long product_id) {
        return this.compareService.removeCompare(customer_id, product_id);
    }
}
