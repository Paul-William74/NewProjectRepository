package com.Ecommerce.demo.DTO.Product.Compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProductCompareDTO {

    private Long id;

    private String name;
    private Map<String, String> materialWithCosts;
    private Map<String, String> materialOnDiscount;
    private String imgUrl; //image reference
    private String price; //image of material must reflect this
    private String discountPrice;

}
