package com.Ecommerce.demo.DTO.Register.Product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ProductSizeRegisterDTO {

    @NotBlank(message = "size cannot be empty")
    private String size;

    @NotBlank(message = "Material Field Needs To Be Filled")
    private String material;

    @NotNull(message = "quantity field cannot be empty")
    private int quantity;
}
