package com.Ecommerce.demo.DTO.LogIn;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class LoggedInUser {

    @NotNull(message = "id cannot be empty")
    private Long id;

    @NotBlank(message = "Email field is required")
    @Email
    private String email;
}
