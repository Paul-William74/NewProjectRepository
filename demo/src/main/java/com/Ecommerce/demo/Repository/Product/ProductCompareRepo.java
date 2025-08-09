package com.Ecommerce.demo.Repository.Product;

import com.Ecommerce.demo.Model.Compare.ProductsCompare;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.User.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCompareRepo extends JpaRepository<ProductsCompare, Long> {

    Optional<ProductsCompare> findByProductAndCustomer(Product product, Customer customer);
}
