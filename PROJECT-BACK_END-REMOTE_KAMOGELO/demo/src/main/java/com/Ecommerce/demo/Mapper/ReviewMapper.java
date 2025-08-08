package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.DTO.Review.QuestionAnswerDisplayDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDisplayDTO;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Review.LIKE_TYPE;
import com.Ecommerce.demo.Model.Review.Question;
import com.Ecommerce.demo.Model.Review.Review;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Repository.LikeReviewRepo;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toReview(ReviewDTO reviewDTO);
    ReviewDTO toReviewDto(Review review);

    @Mappings({
        @Mapping(target = "customerFullName", expression = "java(getCustomerFullName(review))"),
        @Mapping(target = "dateUpdatedAt", expression = "java(getDateUpdated(review))")
    })
    ReviewDisplayDTO toReviewDisplayDTO(Review review);
    List<ReviewDisplayDTO> toReviewDisplayDTO(List<Review> reviewList);
    default String getCustomerFullName(Review review) {
        Customer customer = review.getCustomer();
        return "%s %s".formatted(customer.getFirstName(), customer.getLastName());
    }
    default String getDateUpdated(Review review) {
        if(review.getUpdatedAt() != null)
            return review.getUpdatedAt().toLocalDate().toString();
        return review.getCreatedAt().toLocalDate().toString();
    }









    @Mapping(target = " questionId", source = "id")
    QuestionAnswerDisplayDTO toQuestionAnswerDTO(Question question);
}
