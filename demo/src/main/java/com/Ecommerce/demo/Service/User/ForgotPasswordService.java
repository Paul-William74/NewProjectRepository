package com.Ecommerce.demo.Service.User;

import com.Ecommerce.demo.Components.NotificationHandler;
import com.Ecommerce.demo.Components.OTPGenerator;
import com.Ecommerce.demo.DTO.ForgotPasswordAdminDTO;
import com.Ecommerce.demo.Exception.User.AdminNotFoundException;
import com.Ecommerce.demo.Exception.User.CustomerNotFoundException;
import com.Ecommerce.demo.Model.ForgotPassword.ForgotPassword;
import com.Ecommerce.demo.Model.User.Admin;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.User.User;
import com.Ecommerce.demo.Repository.ForgotPasswordRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ForgotPasswordService extends BaseService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final ForgotPasswordRepo forgotPasswordRepo;
    private final OTPGenerator otpGenerator;
    private final NotificationHandler notificationHandler;


    public ResponseEntity<?> handleAdminVerification(ForgotPasswordAdminDTO adminDTO) {

        Admin admin;
        try {
            admin = findAdmin(adminDTO.getEmail());
        }catch (AdminNotFoundException e) {
            return ResponseEntity.badRequest().body("Invalid Email");
        }

        boolean isValid = this.validateAdminInformation(admin, adminDTO);
        if(!isValid)// if their information does not match
            return ResponseEntity.badRequest().body("Invalid Information");

        sendOTP(admin);
        return ResponseEntity.ok("Verification Successful.Check Your Emails for an OPT to reset your password." +
                "The OPT Will Expire In 10 Minutes");
    }

    public ResponseEntity<?> handleCustomerVerification(String email) {

        Customer customer;
        try {
            customer = findCustomer(email);
        }catch (CustomerNotFoundException ex) {
            return ResponseEntity.badRequest().body("Invalid Email" + email);
        }
        sendOTP(customer);
        return ResponseEntity.ok("Verification Successful.Check Your Emails for an OPT to reset your password." +
                "The OPT Will Expire In 10 Minutes");
    }

    public ResponseEntity<?> handleUserPasswordChange(String email, String newPassword) {

        User user = findUser(email);
        String hashedPassword = passwordEncoder.encode(newPassword); //hash the new password
        user.setPassword(hashedPassword); //set the new hashed Password

        Optional<ForgotPassword> forgotPassword = this.forgotPasswordRepo.findByUser(user);
        if(forgotPassword.isEmpty())
            return ResponseEntity.badRequest().body("An Error Occurred");

        this.forgotPasswordRepo.delete(forgotPassword.get()); //delete that users entry
        this.userRepo.save(user);
        return ResponseEntity.ok("Password Was Successfully Reset");
    }

    public ResponseEntity<?> verifyOTP(String email, String OTP) {

        User user = this.findUser(email);
        Optional<ForgotPassword> optionalForgotPassword = this.forgotPasswordRepo.findByUser(user);
        if(optionalForgotPassword.isEmpty())
            return ResponseEntity.badRequest().body("An Error Occurred");


        if(!OTP.equals(optionalForgotPassword.get().getOTP()))
            return ResponseEntity.badRequest().body("Invalid OTP");
        return ResponseEntity.ok("OTP successfully Verified");
    }

    private void sendOTP(User user) {

        Optional<ForgotPassword> optionalForgotPassword = this.forgotPasswordRepo.findByUser(user);
        if (optionalForgotPassword.isEmpty()) {
            String oneTimePassword = otpGenerator.getOTP();
            ForgotPassword forgotPasswordEntry = new ForgotPassword(oneTimePassword, user);
            this.forgotPasswordRepo.save(forgotPasswordEntry);
            publishOTP_ViaEmail(user, oneTimePassword); //we create a new opt and send
            return;
        }

        ForgotPassword forgotPassword = optionalForgotPassword.get();
        LocalDateTime now = LocalDateTime.now();
        long minutesPassed = Duration.between(forgotPassword.getCreatedAt(), now).toMinutes();

        if (minutesPassed > 10) {
            this.forgotPasswordRepo.delete(forgotPassword);

            //regenerate and resend
            String newOtp = otpGenerator.getOTP();
            ForgotPassword newEntry = new ForgotPassword(newOtp, user);
            this.forgotPasswordRepo.save(newEntry);
            publishOTP_ViaEmail(user, newOtp); //we create a new one since the old one has expired
        }
    }

    private void publishOTP_ViaEmail(User user, String otp) {
        this.notificationHandler.publishUserOTPVerificationEmail(user, otp);
    }

    private boolean validateAdminInformation(Admin admin, ForgotPasswordAdminDTO passwordAdminDTO) {

        return passwordAdminDTO.getFirstName().equalsIgnoreCase(admin.getFirstName()) &&
                passwordAdminDTO.getLastName().equalsIgnoreCase(admin.getLastName());
    }
}
