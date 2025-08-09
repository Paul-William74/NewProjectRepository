package com.Ecommerce.demo.Repository.Product;

import com.Ecommerce.demo.Model.Product.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepo extends JpaRepository<ProductSize, Long> {
}
