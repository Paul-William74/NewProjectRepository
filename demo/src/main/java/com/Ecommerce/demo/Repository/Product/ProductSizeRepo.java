package com.Ecommerce.demo.Repository.Product;

import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ProductSizeRepo extends JpaRepository<ProductSize, Long> {

    Optional<ProductSize> findBySizeAndProduct(Double size, Product product);
    Optional<ProductSize> findByProductAndMaterial(Product product, MATERIAL material);
}
