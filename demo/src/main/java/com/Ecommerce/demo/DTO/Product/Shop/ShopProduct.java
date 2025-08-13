package com.Ecommerce.demo.DTO.Product.Shop;


import com.Ecommerce.demo.Model.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class ShopProduct {

    private Long id;

    private String name;

    private String price;
    private String salePrice;
    private List<String> imgUrls;

    private boolean isNew;
    private boolean isSellingFast;
    private boolean onSale;


}
