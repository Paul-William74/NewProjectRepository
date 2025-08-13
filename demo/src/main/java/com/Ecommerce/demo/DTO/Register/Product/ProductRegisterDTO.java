package com.Ecommerce.demo.DTO.Register.Product;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProductRegisterDTO {

    @NotBlank(message = "Product Name is Empty")
    private String name;

    @NotBlank(message = "Product Description is Empty")
    private String description;

    @NotBlank(message = "Jewellery Type Is Empty")
    private String jeweleryType;

    private List<String> gemStones = new LinkedList<>();

    @Valid
    private ProductPriceRegisterDTO productPrice;


}
