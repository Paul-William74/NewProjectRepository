package com.Ecommerce.demo.DTO.Product.Admin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class AdminProduct {

    private Long id;

    private String name;
    private String description;
    private String jewelleryType;

    private final List<AdminProductImage> productImages = new LinkedList<>();
    private final List<String> gemStones = new LinkedList<>();

}
