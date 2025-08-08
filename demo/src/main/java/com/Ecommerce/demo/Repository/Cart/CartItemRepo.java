package com.Ecommerce.demo.Repository.Cart;

import com.Ecommerce.demo.Model.Cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
