package com.Ecommerce.demo.Repository;

import com.Ecommerce.demo.Model.ForgotPassword.ForgotPassword;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepo extends JpaRepository<ForgotPassword, Long> {

    Optional<ForgotPassword> findByUser(User user);
}
