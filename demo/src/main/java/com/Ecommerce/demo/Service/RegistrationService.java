package com.Ecommerce.demo.Service;

import com.Ecommerce.demo.Components.Publisher.NotificationHandler;
import com.Ecommerce.demo.DTO.Register.ProductRegisterDTO;
import com.Ecommerce.demo.DTO.Register.AdminRegisterDTO;
import com.Ecommerce.demo.DTO.Register.CustomerRegisterDTO;
import com.Ecommerce.demo.DTO.Register.UserRegisterDTO;
import com.Ecommerce.demo.Exception.User.UserNotFoundException;
import com.Ecommerce.demo.Mapper.UserMapper;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final NotificationHandler notificationHandler;

    public ResponseEntity<?> registerUser(UserRegisterDTO userRegisterDTO) {

        if (userRegisterDTO instanceof CustomerRegisterDTO customerRegisterDTO) {
            return handleCustomerRegistration(customerRegisterDTO);
        } else if (userRegisterDTO instanceof AdminRegisterDTO adminRegisterDTO) {
            return handleAdminRegistration(adminRegisterDTO);
        }
        throw new UserNotFoundException("User Could Not Be Found");
    }

    @Transactional
    public ResponseEntity<?> registerProduct(ProductRegisterDTO productRegisterDTO) {
        return null;
    }

    private ResponseEntity<?> handleCustomerRegistration(CustomerRegisterDTO customerRegisterDTO) {

        Customer customer;
        if (customerRegisterDTO.getAddress() == null)
            customer = this.userMapper.mapDefaultCustomer(customerRegisterDTO);
        else
            customer = this.userMapper.toCustomerWishAddress(customerRegisterDTO);

        try {
            customer.setLoyaltyPoints(150); //give them 150 points for registering
            Customer customer_ = this.userRepo.save(customer);

            this.notificationHandler.publishSuccessfulUserRegistrationEmail(customer_);

            return ResponseEntity.ok(customer_);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("Email Already Exists", HttpStatus.CONFLICT);
        }
    }

    private ResponseEntity<?> handleAdminRegistration(AdminRegisterDTO adminRegisterDTO) {
        return null;
    }

}
