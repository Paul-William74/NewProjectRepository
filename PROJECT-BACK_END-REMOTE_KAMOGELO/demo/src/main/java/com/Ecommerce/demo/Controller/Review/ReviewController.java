package com.Ecommerce.demo.Controller.Review;
import com.Ecommerce.demo.DTO.Review.ReviewDTO;
import com.Ecommerce.demo.Service.Review.LikeReviewService;
import com.Ecommerce.demo.Service.Review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final LikeReviewService likeReviewService;
    private final ReviewService reviewService;


    @PostMapping("/addReview/{customer_id}/{product_id}")
    public ResponseEntity<?> addReview(
            @PathVariable final Long customer_id,
            @PathVariable final Long product_id,
            @RequestBody  final ReviewDTO reviewDTO) {
        return this.reviewService.createReview(customer_id, product_id, reviewDTO);
    }


    @PutMapping("/editReview/{customer_id}/{product_id}")
    public ResponseEntity<?> editReview(
            @PathVariable final Long customer_id,
            @PathVariable final Long product_id,
            @RequestBody  final ReviewDTO reviewDTO) {
        return null;
    }

    @DeleteMapping("/removeReview/{customer_id}")
    public ResponseEntity<?> removeReview(
            @PathVariable final Long customer_id) {
        return null;
    }


    @PostMapping("/addRemoveLike/{customer_id}/{review_id}")
    public ResponseEntity<?> addRemoveLike(
            @PathVariable final Long customer_id,
            @PathVariable final Long review_id) {
        return this.likeReviewService.addLike(customer_id, review_id);
    }

    @PostMapping("/addRemoveDislike/{customer_id}/{review_id}")
    public ResponseEntity<?> addRemoveDislike(
        @PathVariable final Long customer_id,
        @PathVariable final Long review_id) {
        return this.likeReviewService.addDislike(customer_id, review_id);
    }


    @GetMapping("/All/{product_id}")
    public ResponseEntity<?> getProductReviews(@PathVariable final Long product_id) {
        return this.reviewService.getProductReviews(product_id);
    }
}
