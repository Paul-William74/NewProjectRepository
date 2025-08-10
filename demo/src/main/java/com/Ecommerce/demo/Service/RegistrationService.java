package com.Ecommerce.demo.Service;

import com.Ecommerce.demo.Components.Publisher.NotificationHandler;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProductImage;
import com.Ecommerce.demo.DTO.Register.Product.ProductImageRegisterDTO;
import com.Ecommerce.demo.DTO.Register.Product.ProductRegisterDTO;
import com.Ecommerce.demo.DTO.Register.AdminRegisterDTO;
import com.Ecommerce.demo.DTO.Register.CustomerRegisterDTO;
import com.Ecommerce.demo.DTO.Register.Product.ProductSizeRegisterDTO;
import com.Ecommerce.demo.DTO.Register.UserRegisterDTO;
import com.Ecommerce.demo.Exception.Enum.JewelleryTyeDoesNotExistException;
import com.Ecommerce.demo.Exception.User.UserNotFoundException;
import com.Ecommerce.demo.Mapper.ProductMapper;
import com.Ecommerce.demo.Mapper.UserMapper;
import com.Ecommerce.demo.Model.Product.*;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Repository.Product.ProductImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService extends BaseService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final NotificationHandler notificationHandler;
    private final ProductImageRepo productImageRepo;


    public ResponseEntity<?> registerUser(UserRegisterDTO userRegisterDTO) {

        if (userRegisterDTO instanceof CustomerRegisterDTO customerRegisterDTO) {
            return handleCustomerRegistration(customerRegisterDTO);
        } else if (userRegisterDTO instanceof AdminRegisterDTO adminRegisterDTO) {
            return handleAdminRegistration(adminRegisterDTO);
        }
        throw new UserNotFoundException("User Could Not Be Found");
    }

    public ResponseEntity<?> registerProduct(ProductRegisterDTO productRegisterDTO) {

        Product product;
        try {
            product = this.productMapper.toProduct(productRegisterDTO);
        }catch (JewelleryTyeDoesNotExistException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        this.productsRepo.save(product);


        return ResponseEntity.ok(null);
    }

    public ResponseEntity<?> registerSizeOrVariant(Long productId, ProductSizeRegisterDTO dto) {

        MATERIAL material = MATERIAL.getMaterialFromLabel(dto.getMaterial());
        Product product = findProduct(productId);
        Double size = Double.parseDouble(dto.getSize());


        return ResponseEntity.ok(null);
    }


    public ResponseEntity<?> registerImage(Long product_Id, ProductImageRegisterDTO productImageRegisterDTO) {

        MATERIAL material = MATERIAL.getMaterialFromLabel(productImageRegisterDTO.getMaterial());
        Product product = findProduct(product_Id);

        ProductImage productImage = new ProductImage(material, product, productImageRegisterDTO.getImgUrl());
        ProductImage newImageEntry = this.productImageRepo.save(productImage);

        AdminProductImage adminProductImage = new AdminProductImage(newImageEntry.getId(),
                productImage.getImgUrl()); //create a product image dto
        return ResponseEntity.ok(adminProductImage); //return the image
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
