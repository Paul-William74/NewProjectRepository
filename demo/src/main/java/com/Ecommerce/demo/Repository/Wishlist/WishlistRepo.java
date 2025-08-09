package com.Ecommerce.demo.Repository.Wishlist;

import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.Wishlist.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

@Repository
public interface WishlistRepo extends JpaRepository<WishList,Long> {

    Optional<WishList> findByProductSizeAndCustomerAndMaterial(ProductSize productSize, Customer customer, MATERIAL material);
}
