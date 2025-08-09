package com.Ecommerce.demo.DTO.Cart;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CartProduct {

    private Long id; //cart item id

    private String name;//

    private Integer quantity; // Quantity of the product in the cart

    private String imgUrl; // URL of the product image

    private Double size; // Size of the product

    private String price; //

    private Set<String> materials = new HashSet<>(); // Set of materials used in the product, e.g., gold, silver, etc.

    private Set<String> gemStones = new HashSet<>(); // Set of gemstones used in the product, e.g., diamond, ruby, etc.


}
