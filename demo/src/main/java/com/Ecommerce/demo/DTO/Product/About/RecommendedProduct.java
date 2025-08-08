package com.Ecommerce.demo.DTO.Product.About;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class RecommendedProduct {


    private Long id;
    private String imageUrl; // URL of the fragrance image
    private String name; // name of the fragrance
    private String price; // starting price of the fragrance
    private String concentration;
}
