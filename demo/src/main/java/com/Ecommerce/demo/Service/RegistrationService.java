package com.Ecommerce.demo.Service;

import com.Ecommerce.demo.Components.Publisher.NotificationHandler;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProductImage;
import com.Ecommerce.demo.DTO.Register.Product.ProductImageRegisterDTO;
import com.Ecommerce.demo.DTO.Register.Product.ProductPriceRegisterDTO;
import com.Ecommerce.demo.DTO.Register.Product.ProductRegisterDTO;
import com.Ecommerce.demo.DTO.Register.AdminRegisterDTO;
import com.Ecommerce.demo.DTO.Register.CustomerRegisterDTO;
import com.Ecommerce.demo.DTO.Register.UserRegisterDTO;
import com.Ecommerce.demo.Exception.User.UserNotFoundException;
import com.Ecommerce.demo.Mapper.ProductMapper;
import com.Ecommerce.demo.Mapper.ProductPriceMapper;
import com.Ecommerce.demo.Mapper.UserMapper;
import com.Ecommerce.demo.Model.Product.*;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Repository.Product.ProductImageRepo;
import jakarta.transaction.Transactional;
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
    private final ProductPriceMapper productPriceMapper;
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

    @Transactional
    public ResponseEntity<?> registerProduct(ProductRegisterDTO productRegisterDTO) {

        Product product = productMapper.toProduct(productRegisterDTO);
        ProductPrice productPrice = productPriceMapper.toProductPrice(productRegisterDTO.getProductPrice());
        productPrice.setProduct(product); //reference the product back

        product.getProductPrices().add(productPrice); //add that product price to the products list to save
        this.productsRepo.save(product); //save changes to the database
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<?> registerProductPrice(Long product_id, ProductPriceRegisterDTO productPriceRegisterDTO) {

        Product product = findProduct(product_id);
        ProductPrice productPrice = productPriceMapper.toProductPrice(productPriceRegisterDTO);

        productPrice.setProduct(product); // set the product reference;

        return ResponseEntity.ok(productPrice);
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
