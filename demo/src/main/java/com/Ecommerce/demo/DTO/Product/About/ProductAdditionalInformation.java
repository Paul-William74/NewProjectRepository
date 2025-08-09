package com.Ecommerce.demo.DTO.Product.About;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProductAdditionalInformation {

    private List<String> materials = new LinkedList<>(); // the materials used to make the product
    private List<String> gemstones = new LinkedList<>(); // the gemstones used in the product
    private List<Double> sizes = new LinkedList<>(); // the sizes of the product
}
