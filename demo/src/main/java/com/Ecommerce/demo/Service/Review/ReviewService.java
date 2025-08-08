package com.Ecommerce.demo.Service.Review;

import com.Ecommerce.demo.DTO.Product.Review.ProductReview;
import com.Ecommerce.demo.DTO.Product.Review.ProductReviewsContainer;
import com.Ecommerce.demo.DTO.Review.QuestionAnswerDisplayDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDisplayContainerDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDisplayDTO;
import com.Ecommerce.demo.Exception.Product.ProductNotFoundException;
import com.Ecommerce.demo.Exception.Review.ReviewNotFoundException;
import com.Ecommerce.demo.Exception.User.CustomerNotFoundException;
import com.Ecommerce.demo.Mapper.ReviewMapper;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Review.LIKE_TYPE;
import com.Ecommerce.demo.Model.Review.Question;
import com.Ecommerce.demo.Model.Review.Review;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Repository.*;
import com.Ecommerce.demo.Repository.Review.LikeReviewRepo;
import com.Ecommerce.demo.Repository.Review.ReviewRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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

        //Checking if the customer exists
        try{
            customer=findCustomer(customerId);
            product=findProduct(productId);
        }catch (CustomerNotFoundException ex){
            return new ResponseEntity<>("Invalid customer Id", HttpStatus.BAD_REQUEST);
        }catch (ProductNotFoundException ex){
            return new ResponseEntity<>("Invalid product Id", HttpStatus.BAD_REQUEST);
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

    public ResponseEntity<?>editReview(Long customerId, Long productId, Long reviewId,ReviewDTO reviewDTO){

        Review review;
        Customer customer;
        Product product;
        //check if Review, Customer or Product exists
        try{
            review=findReview(reviewId);
            customer=findCustomer(customerId);
            product=findProduct(productId);

        }catch (ReviewNotFoundException ex) {
            return new ResponseEntity<>("Review does not exist", HttpStatus.BAD_REQUEST);
        }catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>("Customer does not exist", HttpStatus.BAD_REQUEST);
        }catch (ProductNotFoundException ex) {
            return new ResponseEntity<>("Product does not exist", HttpStatus.BAD_REQUEST);
        }

        //Checking if Customer is valid for editing or the right product's review is edited
        Customer reviewCustomer=review.getCustomer();
        Product reviewProduct= review.getProduct();
        if(reviewCustomer!=customer||reviewProduct!=product){
            return ResponseEntity.badRequest().body("Cannot edit review; Invalid customer or product");
        }

        review.setHeader(reviewDTO.getHeader());
        review.setText(reviewDTO.getText());
        review.setRating(reviewDTO.getRating());

        Review saved=reviewRepo.save(review);
        ReviewDTO dto=reviewMapper.toReviewDto(saved);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?>deleteReview(Long customerId, Long productId, Long reviewId){

        Review review;
        Customer customer;
        Product product;
        //check if Review, Customer or Product exists
        try{
            review=findReview(reviewId);
            customer=findCustomer(customerId);
            product=findProduct(productId);

        }catch (ReviewNotFoundException ex) {
            return new ResponseEntity<>("Review does not exist", HttpStatus.BAD_REQUEST);
        }catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>("Customer does not exist", HttpStatus.BAD_REQUEST);
        }catch (ProductNotFoundException ex) {
            return new ResponseEntity<>("Product does not exist", HttpStatus.BAD_REQUEST);
        }
        //Checking if Customer is valid for editing or the right product's review is edited
        Customer reviewCustomer=review.getCustomer();
        Product reviewProduct= review.getProduct();
        if(reviewCustomer!=customer||reviewProduct!=product){
            return ResponseEntity.badRequest().body("Cannot edit review; Invalid customer or product");
        }
        reviewRepo.delete(review);

        return ResponseEntity.ok("Successfully deleted review");
    }


    public ResponseEntity<?> getProductReviews(Long product_id) {

        Product product = findProduct(product_id);

        List<Review> reviewList = this.reviewRepo.findByProductId(product_id);

        if(reviewList.isEmpty())
            return ResponseEntity.ok(new ProductReviewsContainer());


        ProductReviewsContainer productReviewsContainer = new ProductReviewsContainer();
        List<ProductReview> productReviews = this.reviewMapper.toProductReviews(reviewList); //create a list of ProductReview DTOs from the review list

        double totalRating = this.reviewRepo.findAverageRatingByProductId(product_id);
        int reviewCount = this.reviewRepo.countByProductId(product_id); //get the total number of reviews for that product
        Map<Integer, Integer> ratingMapper = getRatingMapper(reviewList); //get the rating mapper for the product reviews

        productReviewsContainer.setReviewList(productReviews); //set the reviews in the container
        productReviewsContainer.setTotalRating(totalRating); //set the total rating of the product
        productReviewsContainer.setReviewsCount(reviewCount); //set the total number of reviews for that product
        productReviewsContainer.setRatingsCount(ratingMapper); //set the rating mapper for the product reviews

        return ResponseEntity.ok(productReviewsContainer);
    }

    private List<ReviewDisplayDTO> getReviewDisplayDTOs(List<Review> reviewList) {
        return new LinkedList<>();
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


    private Map<Integer, Integer> getRatingMapper(List<Review> reviewList) {
        Map<Integer, Integer> ratingMapper = new LinkedHashMap<>();
        for(int i = 1; i <= 5; i++)
            ratingMapper.put(i, 0); //initialize the map with all ratings set to 0
        for(Review review: reviewList) {
            int rating = (int) Math.floor(review.getRating());

            for (int i = 1; i <= 5; i++)
                if(rating ==i)
                    ratingMapper.computeIfPresent(i, //get the current count of that rating
                            (k, currentCount) -> currentCount + 1); //increment the count by
        }
        return ratingMapper;
    }
}
