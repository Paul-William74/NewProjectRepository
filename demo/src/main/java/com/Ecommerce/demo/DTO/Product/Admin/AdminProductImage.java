package com.Ecommerce.demo.DTO.Product.Admin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class AdminProductImage {

    private Long id;
    private String imgUrl;
}
