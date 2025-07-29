package com.Ecommerce.demo.DTO.Register;

import com.Ecommerce.demo.DTO.Address.AddressRequestDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerRegisterDTO.class, name = "CUSTOMER")
})
public abstract class UserRegisterDTO {

    @NotBlank(message = "first name is required")
    protected String firstName;

    @NotBlank(message = "last name is required")
    protected String lastName;

    @NotBlank(message = "email is required")
    @Email(message = "the provided email is not valid")
    protected String email;

    @NotBlank(message = "An error Occurred")
    @Pattern(regexp = "^(CUSTOMER|ADMIN)$", message = "An Error Occurred")
    private String userType;

    @Pattern(regexp = "^(\\+27|0)[6-8][0-9]{8}$", message = "Invalid Phone Number")
    protected String phoneNumber;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password must at least be 8 characters long")
    protected String password;


    @Valid //will only be validated once the user starts entering information
    protected AddressRequestDTO address;
}
