package com.Ecommerce.demo.DTO.Register;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProductRegisterDTO {

    private String name;
    private String description;
    private String k;
    // more will be implemented later
}
