package com.Ecommerce.demo.Service;

import com.Ecommerce.demo.Exception.Product.ProductCompareEntryNotFoundException;
import com.Ecommerce.demo.Exception.Product.ProductNotFoundException;
import com.Ecommerce.demo.Exception.Review.ReviewNotFoundException;
import com.Ecommerce.demo.Exception.User.AdminNotFoundException;
import com.Ecommerce.demo.Exception.User.CustomerNotFoundException;
import com.Ecommerce.demo.Exception.User.UserNotFoundException;
import com.Ecommerce.demo.Model.Cart.CART_STATUS;
import com.Ecommerce.demo.Model.Cart.Cart;
import com.Ecommerce.demo.Model.Compare.ProductsCompare;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Review.Review;
import com.Ecommerce.demo.Model.User.Admin;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.User.User;
import com.Ecommerce.demo.Repository.*;
import com.Ecommerce.demo.Repository.Cart.CartRepo;
import com.Ecommerce.demo.Repository.Product.ProductCompareRepo;
import com.Ecommerce.demo.Repository.Product.ProductsRepo;
import com.Ecommerce.demo.Repository.Review.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class BaseService {

    @Autowired protected UserRepo userRepo;
    @Autowired protected CartRepo cartRepo;

    @Autowired protected ProductsRepo productsRepo;
    @Autowired protected ProductCompareRepo productCompareRepo;

    @Autowired protected ReviewRepo reviewRepo;


    protected Customer findCustomer(Long customer_id) {
        return this.userRepo.findById(customer_id)
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new CustomerNotFoundException("Customer does not exist"));
    }

    protected Customer findCustomer(String email) {
        return this.userRepo.findByEmail(email)
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Does Not Exist"));
    }

    protected User findUser(String email) {
        return this.userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Was Not Found"));
    }

    protected User findUser(Long user_id) {
        return this.userRepo.findById(user_id)
                .orElseThrow(() -> new UserNotFoundException("User Was Not Found"));
    }

    protected Admin findAdmin(Long admin_id) {
        return this.userRepo.findById(admin_id)
                .filter(user -> user instanceof Admin)
                .map(user -> (Admin) user)
                .orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
    }

    protected Admin findAdmin(String email) {
        return this.userRepo.findByEmail(email)
                .filter(user -> user instanceof Admin)
                .map(user -> (Admin) user)
                .orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
    }

    protected Product findProduct(Long product_id) {
        return this.productsRepo.findById(product_id)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist"));
    }


    protected ProductsCompare findProductsCompare(Customer customer, Product product) {
        return this.productCompareRepo.findByProductAndCustomer(product, customer)
                .orElseThrow(() -> new ProductCompareEntryNotFoundException("Compare Entry Not Found"));
    }

    protected Review findReview(Long review_id) {
        return this.reviewRepo.findById(review_id)
                .orElseThrow(() -> new ReviewNotFoundException("Review Does Not Exist"));
    }

    protected Cart findCustomerCart(Customer customer, CART_STATUS cartStatus) {
        return this.cartRepo.findByCustomerAndCartStatus(customer, cartStatus)
                .orElseThrow(() -> new CustomerNotFoundException("Cart Does Not Exist"));
    }

 }
