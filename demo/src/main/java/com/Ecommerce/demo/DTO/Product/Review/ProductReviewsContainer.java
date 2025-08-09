package com.Ecommerce.demo.DTO.Product.Review;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ProductReviewsContainer {

    private List<ProductReview> reviewList = new LinkedList<>();

    private int reviewsCount;

    private double totalRating = 0.0; // the total rating of the product

    private Map<Integer, Integer> ratingsCount = new LinkedHashMap<>(); // the count of each rating from 1 to 5


}
