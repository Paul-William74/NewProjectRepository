package com.Ecommerce.demo.Repository.Product;

import com.Ecommerce.demo.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Product, Long> {

}
