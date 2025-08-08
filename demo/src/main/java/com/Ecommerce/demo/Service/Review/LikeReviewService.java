package com.Ecommerce.demo.Service.Review;

import com.Ecommerce.demo.Model.Review.LIKE_TYPE;
import com.Ecommerce.demo.Model.Review.LikeReview;
import com.Ecommerce.demo.Model.Review.Review;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Repository.Review.LikeReviewRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeReviewService extends BaseService {

    private final LikeReviewRepo likeReviewRepo;

    @Transactional
    public ResponseEntity<?> addLike(Long customer_id, Long review_id) {

        Customer customer = findCustomer(customer_id);
        Review review = findReview(review_id);

        // Check if user already liked this review
        Optional<LikeReview> existingLikeOpt = likeReviewRepo.findByCustomerIdAndReviewId(customer.getId(), review.getId());

        if (existingLikeOpt.isPresent()) {
            LikeReview existingLike = existingLikeOpt.get();

            // If it's already a like, remove it (toggle off)
            if (existingLike.getLikeType().equals(LIKE_TYPE.LIKE))
                likeReviewRepo.delete(existingLike);

            // If it's a dislike, change it to a like (toggle switch)
            else
                existingLike.setLikeType(LIKE_TYPE.LIKE);
        }
        // If no existing reaction, create new like
        else {

            LikeReview like = new LikeReview(null, LIKE_TYPE.LIKE, customer, review);
            likeReviewRepo.save(like);
        }
            return ResponseEntity.ok("");
    }

    @Transactional
    public ResponseEntity<?> addDislike(Long customer_id, Long review_id) {

        Customer customer = findCustomer(customer_id);
        Review review = findReview(review_id);

        // Check if user already liked this review
        Optional<LikeReview> existingDislikeOpt = likeReviewRepo.findByCustomerIdAndReviewId(customer.getId(), review.getId());

        if (existingDislikeOpt.isPresent()) {
            LikeReview existingLike = existingDislikeOpt.get();

            // If it's already a dislike, remove it (toggle off)
            if (existingLike.getLikeType().equals(LIKE_TYPE.DISLIKE))
                likeReviewRepo.delete(existingLike);

            // If it's a dislike, change it to a like (toggle switch)
            else
                existingLike.setLikeType(LIKE_TYPE.DISLIKE);
        }
        // If no existing reaction, create new like
        else {
            LikeReview disLike = new LikeReview(null, LIKE_TYPE.DISLIKE, customer, review);
            likeReviewRepo.save(disLike);
        }
        return ResponseEntity.ok("");
    }
}
