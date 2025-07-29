package com.Ecommerce.demo.Controller.Customer;


import com.Ecommerce.demo.Service.User.Customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/orders/{customer_id}")
    public ResponseEntity<?> geCustomerOrders(@PathVariable final Long customer_id) {
        return this.customerService.getCustomerOrders();
    }

    @GetMapping("/info/{customer_id}")
    public ResponseEntity<?> getCustomerInformation(@PathVariable final Long customer_id) {
        return this.customerService.getCustomerInformation();
    }

}
