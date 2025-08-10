package com.Ecommerce.demo.DTO.Register;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private List<String> gemStones;

    @NotNull(message = "Price is Empty")
    private Double basePrice;


}
