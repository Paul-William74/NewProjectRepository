package com.Ecommerce.demo.DTO.Register.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceRegisterDTO {

    @NotBlank(message = "material filed Is Empty")
    private String material;

    @NotNull(message = "base Price field Is Empty")
    private double basePrice;
}
