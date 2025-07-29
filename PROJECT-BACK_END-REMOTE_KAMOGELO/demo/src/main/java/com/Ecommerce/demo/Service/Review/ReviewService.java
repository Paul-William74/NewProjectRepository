package com.Ecommerce.demo.Service.Review;

import com.Ecommerce.demo.DTO.Review.QuestionAnswerDisplayDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDisplayContainerDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDisplayDTO;
import com.Ecommerce.demo.Exception.Product.ProductNotFoundException;
import com.Ecommerce.demo.Exception.Review.ReviewNotFoundException;
import com.Ecommerce.demo.Exception.User.CustomerNotFoundException;
import com.Ecommerce.demo.Mapper.ReviewMapper;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Review.Answer;
import com.Ecommerce.demo.Model.Review.LIKE_TYPE;
import com.Ecommerce.demo.Model.Review.Question;
import com.Ecommerce.demo.Model.Review.Review;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Repository.*;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService extends BaseService {

    private final ReviewRepo reviewRepo;
    private final LikeReviewRepo likeReviewRepo;
    private final ReviewMapper reviewMapper;

    private final CollaborationQuestionRepo collaborationQuestionRepo;
    private final QuestionsRepo questionsRepo;
    private final AnswersRepo answersRepo;

    public ResponseEntity<?>createReview(Long customerId, Long productId, ReviewDTO reviewDTO){

        Customer customer;
        Product product;

        try{
            customer=findCustomer(customerId);
            product=findProduct(productId);
        }catch (CustomerNotFoundException ex){
            return new ResponseEntity<>("Invalid customer Id", HttpStatus.NOT_FOUND);
        }catch (ProductNotFoundException ex){
            return new ResponseEntity<>("Invalid product Id", HttpStatus.NOT_FOUND);
        }

        //check if Review exists
         if (reviewRepo.findByCustomerIdAndProductId(customerId, productId).isPresent()){
            return ResponseEntity.badRequest().body("Review already exists");
        }

        Review review=reviewMapper.toReview(reviewDTO);
        review.setCustomer(customer);
        review.setProduct(product);

        ReviewDTO saved=reviewMapper.toReviewDto(reviewRepo.save(review));

        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?>editReview(Long reviewId,ReviewDTO reviewDTO){

        Review review;
        //check if Review exists
        try{
            review=findReview(reviewId);

        }catch (ReviewNotFoundException ex){
            return new ResponseEntity<>("Review does not exist",HttpStatus.NOT_FOUND);
        }

        review.setHeader(reviewDTO.getHeader());
        review.setText(reviewDTO.getText());
        review.setRating(reviewDTO.getRating());

        Review saved=reviewRepo.save(review);
        ReviewDTO dto=reviewMapper.toReviewDto(saved);
        //Just to see if it works, will be removed later
        //dto.setUpdatedAt(saved.getUpdatedAt());

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?>deleteReview(Long reviewId){

        Review review;
        //check if Review exists
        try{
            review=findReview(reviewId);

        }catch (ReviewNotFoundException ex){
            return new ResponseEntity<>("Review does not exist",HttpStatus.NOT_FOUND);
        }
        reviewRepo.delete(review);

        return ResponseEntity.ok("Successfully deleted review");
    }

    public ResponseEntity<?> getProductReviews(Long product_id) {

        Product product;
        try {
            product = findProduct(product_id); //find the product
        }catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body("Product does not exist");
        }

        List<Review> reviewList = this.reviewRepo.findByProductId(product.getId()); //look for the reviews by their products
        List<ReviewDisplayDTO> reviewDisplayDTOS = this.getReviewDisplayDTOs(reviewList);
        List<QuestionAnswerDisplayDTO> questionAnswerDisplayDTOS = this.getQuestionAnswersDTOs(product);

        //creating the container to house everything
        ReviewDisplayContainerDTO containerDTO = new ReviewDisplayContainerDTO(
                reviewDisplayDTOS,
                questionAnswerDisplayDTOS);

        return ResponseEntity.ok(containerDTO);
    }

    private List<ReviewDisplayDTO> getReviewDisplayDTOs(List<Review> reviewList) {
        List<ReviewDisplayDTO> reviewDisplayDTOS = new ArrayList<>();
        for(Review review: reviewList) {
            ReviewDisplayDTO reviewDisplayDTO = this.reviewMapper.toReviewDisplayDTO(review);
            reviewDisplayDTO.setDislikes(this.likeReviewRepo.countByReviewIdAndLikeType(review.getId(), LIKE_TYPE.DISLIKE));
            reviewDisplayDTO.setLikes(this.likeReviewRepo.countByReviewIdAndLikeType(review.getId(), LIKE_TYPE.LIKE));
            reviewDisplayDTOS.add(reviewDisplayDTO);
        }
        return reviewDisplayDTOS;
    }

    private List<QuestionAnswerDisplayDTO> getQuestionAnswersDTOs(Product product) {

        List<Question> productList = this.questionsRepo.findByProductId(product.getId()); // find the questions for that product
        if(productList.isEmpty())
            return new ArrayList<>(); //if it's empty, don't bother doing operations that require more processing

        List<QuestionAnswerDisplayDTO> questionAnswerDisplayDTOList = new ArrayList<>();
        for(Question question: productList) {
            QuestionAnswerDisplayDTO questionAnswerDisplayDTO = this.reviewMapper.toQuestionAnswerDTO(question);

            questionAnswerDisplayDTO.setQuestionResponse("No Response Yet");
            //find the answer to that question and if its present set it to the response
            answersRepo.findByQuestionId(question.getId())
                    .ifPresent(answer ->
                            questionAnswerDisplayDTO.setQuestionResponse(answer.getResponseAnswer()));

            questionAnswerDisplayDTOList.add(questionAnswerDisplayDTO); //add that to the list
        }

        return questionAnswerDisplayDTOList;
    }
}
