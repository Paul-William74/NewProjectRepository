package com.Ecommerce.demo.Service.Cart;


import com.Ecommerce.demo.Service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService extends BaseService {



    public ResponseEntity<?> getCartItems(Long customerId) {

       // Customer customer = findCustomer(customerId); //find the customer by ID
       // Cart cart = this.findCustomerCart(customer, CART_STATUS.ACTIVE); //find the customer's active cart

        return ResponseEntity.ok("Cart items retrieved successfully");
    }
}
