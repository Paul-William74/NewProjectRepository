package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.DTO.Product.Review.ProductReview;
import com.Ecommerce.demo.DTO.Product.Review.ProductReviewsContainer;
import com.Ecommerce.demo.DTO.Review.QuestionAnswerDisplayDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDTO;
import com.Ecommerce.demo.DTO.Review.ReviewDisplayDTO;
import com.Ecommerce.demo.Model.Review.Question;
import com.Ecommerce.demo.Model.Review.Review;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.NamedEntityGraph;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toReview(ReviewDTO reviewDTO);
    ReviewDTO toReviewDto(Review review);


    default String getCustomerLastNameAndInitial(Review review) {
        Customer customer = review.getCustomer();

        String stringInitial = String.valueOf(customer.getFirstName().charAt(0)).toUpperCase();
        char charInitial = stringInitial.charAt(0);
        return "%s %s".formatted(customer.getLastName(), charInitial);
    }

    default String getDateUpdated(Review review) {
        if(review.getUpdatedAt() != null)
            return review.getUpdatedAt().toLocalDate().toString();
        return review.getCreatedAt().toLocalDate().toString();
    }

    default String getCustomerImageUrl(Customer customer) {
        if(customer.getImgUrl() != null)
            return customer.getImgUrl();
        return "";
    }

    default String getReviewText(Review review) {
        if(review.getText() != null)
            return review.getText();
        return "";
    }





    @Mappings({
            @Mapping(target = "customerNameInitials", expression = "java(getCustomerLastNameAndInitial(review))"),
            @Mapping(target = "customerImgUrl", expression = "java(getCustomerImageUrl(review.getCustomer()))"),
            @Mapping(target = "reviewText", expression = "java(getReviewText(review))"),
            @Mapping(target = "updatedAt", expression = "java(getDateUpdated(review))"),
    })
    ProductReview toProductReview(Review review);
    List<ProductReview> toProductReviews(List<Review> reviews);

    @Mapping(target = " questionId", source = "id")
    QuestionAnswerDisplayDTO toQuestionAnswerDTO(Question question);

}
