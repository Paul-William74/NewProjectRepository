package com.Ecommerce.demo.Repository.Product;

import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepo extends JpaRepository<ProductPrice,Long> {

    Optional<ProductPrice> findByProduct(Product product);
}
