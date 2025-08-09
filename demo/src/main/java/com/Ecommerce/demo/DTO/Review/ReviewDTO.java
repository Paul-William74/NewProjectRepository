package com.Ecommerce.demo.DTO.Review;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class ReviewDTO {

    @NotBlank(message = "text must not be empty")
    private String text;

    @NotBlank(message = "rating must not be empty")
    private Double rating;

    @NotBlank(message = "header must not be empty")
    private String header;

}
