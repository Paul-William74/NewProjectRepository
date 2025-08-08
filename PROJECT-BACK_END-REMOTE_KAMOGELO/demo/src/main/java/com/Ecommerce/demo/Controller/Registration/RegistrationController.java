package com.Ecommerce.demo.Controller.Registration;

import com.Ecommerce.demo.DTO.Register.ProductRegisterDTO;
import com.Ecommerce.demo.DTO.Register.UserRegisterDTO;
import com.Ecommerce.demo.Service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST controller for handling user and product registration.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Registers a new user.
     *
     * @param userRegisterDTO the user registration data
     * @return ResponseEntity with registration result
     */
    @PostMapping("/register-user")
    public ResponseEntity<?> register(@Valid @RequestBody final UserRegisterDTO userRegisterDTO) {
        return this.registrationService.registerUser(userRegisterDTO);
    }

    /**
     * Registers a new product.
     *
     * @param productRegisterDTO the product registration data
     * @return ResponseEntity with registration result
     */
    @PostMapping("/register-product")
    public ResponseEntity<?> register(@Valid @RequestBody final ProductRegisterDTO productRegisterDTO) {
        return this.registrationService.registerProduct(productRegisterDTO);
    }
}