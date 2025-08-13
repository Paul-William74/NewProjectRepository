package com.Ecommerce.demo.DTO.Product.Admin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AdminProductPrice {

    private Long id;
    private String price;
    private String material;
    private List<String> gemStones;

    private boolean isOnSale;
    private String discountPrice;
    private String discountPercentage;
}