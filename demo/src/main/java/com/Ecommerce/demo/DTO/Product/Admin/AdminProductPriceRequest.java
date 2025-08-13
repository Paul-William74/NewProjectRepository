package com.Ecommerce.demo.DTO.Product.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class AdminProductPriceRequest {

    private Double price;
    private String material;
    private List<String> gemStones;

    private boolean isOnSale;
    private Double discountPrice;
    private Double discountPercentage;
}

