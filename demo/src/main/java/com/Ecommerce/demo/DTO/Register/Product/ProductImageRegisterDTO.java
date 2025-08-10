package com.Ecommerce.demo.DTO.Register.Product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ProductImageRegisterDTO {

    @NotBlank(message = "image url cannot be empty")
    private String imgUrl;

    @NotBlank(message = "Material Field Needs To Be Filled")
    private String material;
}
