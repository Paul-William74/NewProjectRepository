package com.Ecommerce.demo.DTO.Product.About;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AboutProduct {

    private String name; //name of the jewelry
    private String description; //description of the jewelry


    private Map<String, List<String>> materialImages = new LinkedHashMap<>(); // the materials used to make the product
    private List<String> gemstones = new LinkedList<>(); // the gemstones used in the product

    private double totalRating = 0.0; // the total rating of the product


    private boolean onDiscount = false; // if the product is on discount or not
    private double discountPercentage = 0.0; // the percentage of discount on the product
    private String discountPrice; // the price of the product after discount
    private String  price; // the price of the product

    private AboutSize aboutSize; // the size of the product
}
