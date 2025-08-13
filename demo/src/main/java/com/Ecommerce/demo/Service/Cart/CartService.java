package com.Ecommerce.demo.Service.Cart;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Cart.CartProduct;
import com.Ecommerce.demo.DTO.Cart.CartProductsContainer;
import com.Ecommerce.demo.Mapper.CartMapper;
import com.Ecommerce.demo.Model.Cart.CART_STATUS;
import com.Ecommerce.demo.Model.Cart.Cart;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService extends BaseService {


    private final CartMapper cartMapper;
    private final Formatter formatter;

    public ResponseEntity<?> getCartItems(Long customerId) {

        Customer customer = this.findCustomer(customerId); //find the customer by ID
        Cart cart = this.findCustomerCart(customer, CART_STATUS.ACTIVE); //find that customer's active cart

        return ResponseEntity.ok(null); //return the CartProductsContainer
    }
}
