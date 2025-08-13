package com.Ecommerce.demo.Controller.Registration;

import com.Ecommerce.demo.DTO.Register.Product.ProductImageRegisterDTO;
import com.Ecommerce.demo.DTO.Register.Product.ProductRegisterDTO;
import com.Ecommerce.demo.DTO.Register.UserRegisterDTO;
import com.Ecommerce.demo.Service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/register-products")
    public ResponseEntity<?> register(@Valid @RequestBody final List<ProductRegisterDTO> productRegisterDTOList) {
        return this.registrationService.registerProducts(productRegisterDTOList);
    }


    @PostMapping("/register-image/{product_id}")
    public ResponseEntity<?> register(
            @Valid @RequestBody final ProductImageRegisterDTO productImageRegisterDTO,
            @PathVariable final Long product_id
            ) {
        return this.registrationService.registerImage(product_id, productImageRegisterDTO);
    }

}