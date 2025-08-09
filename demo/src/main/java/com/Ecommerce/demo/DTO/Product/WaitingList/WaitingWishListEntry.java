package com.Ecommerce.demo.DTO.Product.WaitingList;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public final class WaitingWishListEntry {

    private Long id;

    private String[] imgUrls = new String[2];

    private String productName;

    private String price;

    private String description;

    private Double size;
}
