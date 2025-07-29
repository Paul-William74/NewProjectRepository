package com.Ecommerce.demo.Repository;

import com.Ecommerce.demo.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Product, Long> {
}
