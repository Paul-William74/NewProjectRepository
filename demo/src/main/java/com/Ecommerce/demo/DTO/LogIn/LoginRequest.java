package com.Ecommerce.demo.DTO.LogIn;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class LoginRequest {

    @NotBlank(message = "Email field is required")
    @Email
    private String email;

    @NotBlank(message = "Password field is required")
    @Size(message = "Password must be a minimum of 8 characters long", min = 8)
    private String password;
}
