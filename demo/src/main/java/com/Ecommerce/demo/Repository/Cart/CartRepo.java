package com.Ecommerce.demo.Repository.Cart;

import com.Ecommerce.demo.Model.Cart.CART_STATUS;
import com.Ecommerce.demo.Model.Cart.Cart;
import com.Ecommerce.demo.Model.User.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomerAndCartStatus(Customer customer, CART_STATUS cartStatus);
}
