package com.Ecommerce.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ForgotPasswordAdminDTO {

    @NotBlank( message = "first-name is Empty")
    private String firstName;
    @NotBlank(message = "last-name is Empty")
    private String lastName;

    @NotBlank(message = "email is Empty")
    private String email;

    @NotBlank(message = "brand name is Empty")
    private String brandName;

}
