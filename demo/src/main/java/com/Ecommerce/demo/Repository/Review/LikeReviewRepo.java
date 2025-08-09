package com.Ecommerce.demo.Repository.Review;

import com.Ecommerce.demo.Model.Review.LIKE_TYPE;
import com.Ecommerce.demo.Model.Review.LikeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeReviewRepo extends JpaRepository<LikeReview, Long> {

    Optional<LikeReview> findByCustomerIdAndReviewId(Long customer_id, Long review_id);
    int countByReviewIdAndLikeType(Long review_id, LIKE_TYPE likeType);
    int countByLikeType(LIKE_TYPE likeType);
}
