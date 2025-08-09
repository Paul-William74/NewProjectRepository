package com.Ecommerce.demo.Repository.Review;

import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    Optional<Review> findByCustomerIdAndProductId(Long customerId, Long productId);
    List<Review> findByProductId(Long product_id);
    List<Review> findByProductIdOrderByRatingDesc(Long productId);
    List<Review> findByProductIdOrderByRatingDesc(Product product);

    int countByProductId(Long productId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = ?1")
    double findAverageRatingByProductId(Long productId);

}

