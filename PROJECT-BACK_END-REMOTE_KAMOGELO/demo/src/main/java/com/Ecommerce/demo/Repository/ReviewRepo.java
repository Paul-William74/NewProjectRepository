package com.Ecommerce.demo.Repository;

import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Review.LIKE_TYPE;
import com.Ecommerce.demo.Model.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    Optional<Review> findByCustomerIdAndProductId(Long customerId, Long productId);
    List<Review> findByProductId(Long product_id);
    List<Review> findByProduct(Product product);
    List<Review> findByProductIdOrderByRatingDesc(Long productId);
    List<Review> findByProductIdOrderByRatingDesc(Product product);

}

