package com.Ecommerce.demo.DTO.Product.Review;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProductReview {

    private Long id;

    private String updatedAt; // the date and time when the review was last updated

    private String customerNameInitials; // the initials of the customer with their last name who wrote the review

    private String customerImgUrl; // the URL of the customer's profile image

    private String reviewText; // the text of the review

    private String header; // the header of the review

    private Double rating; // the rating given by the customer for the product

}
