package com.Ecommerce.demo.Controller.User;

import com.Ecommerce.demo.DTO.ForgotPasswordAdminDTO;
import com.Ecommerce.demo.Service.User.ForgotPasswordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgotPasswords")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/verifyCustomer")
    public ResponseEntity<?> handleCustomerVerification(@RequestParam final String email) {
        return this.forgotPasswordService.handleCustomerVerification(email);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<?> handleUserPasswordReset(
            @RequestParam final String email,
            @RequestParam final String newPassword) {
        return this.forgotPasswordService.handleUserPasswordChange(email, newPassword);
    }

    @PostMapping("/verifyOTP")
    public ResponseEntity<?> verifyOTP(
            @RequestParam final String email,
            @RequestParam final String OTP) {
        return this.forgotPasswordService.verifyOTP(email, OTP);
    }

    @PostMapping("/verifyAdmin")
    public ResponseEntity<?> handleAdminVerification(@RequestBody @Valid final ForgotPasswordAdminDTO adminDTO){
        return this.forgotPasswordService.handleAdminVerification(adminDTO);
    }
}
