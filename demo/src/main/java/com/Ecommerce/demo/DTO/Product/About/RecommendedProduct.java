package com.Ecommerce.demo.DTO.Product.About;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public final class RecommendedProduct {


    private Long id;

    private List<String> imageUrls = new LinkedList<>(); // URL of the fragrance image
    private String name; // name of the fragrance
    private String price; // starting price of the fragrance
    private boolean isOnSale;
    private String salePrice;


}
