package com.Ecommerce.demo.DTO.Review;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class ReviewDisplayDTO {

    private Long id;

    private String header;
    private String text;
    private String customerFullName;
    private String dateUpdatedAt;

    private double rating;

    private int likes;
    private int dislikes;
}
