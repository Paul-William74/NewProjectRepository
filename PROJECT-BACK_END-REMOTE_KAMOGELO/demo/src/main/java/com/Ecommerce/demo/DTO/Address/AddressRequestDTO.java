package com.Ecommerce.demo.DTO.Address;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class AddressRequestDTO {

    @NotBlank(message = "city field is required")
    private String city;

    @NotBlank(message = "province field is required")
    private String province;

    @NotBlank(message = "postal code filed is required")
    private String postalCode;

    @NotBlank(message = "street field is required")
    private String street;
}
