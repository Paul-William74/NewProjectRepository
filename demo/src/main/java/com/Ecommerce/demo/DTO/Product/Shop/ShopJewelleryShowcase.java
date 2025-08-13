package com.Ecommerce.demo.DTO.Product.Shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ShopJewelleryShowcase {

    private Long id;
    private String jewelleryType;
    private String imageUrl;
}
