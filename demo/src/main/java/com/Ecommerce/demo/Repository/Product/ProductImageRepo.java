package com.Ecommerce.demo.Repository.Product;

import com.Ecommerce.demo.Model.Product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductImage,Long> {
}
