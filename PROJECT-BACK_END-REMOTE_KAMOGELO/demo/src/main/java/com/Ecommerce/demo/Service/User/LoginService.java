package com.Ecommerce.demo.Service.User;
import com.Ecommerce.demo.DTO.LogIn.LoggedInUser;
import com.Ecommerce.demo.DTO.LogIn.LoginRequest;
import com.Ecommerce.demo.DTO.Register.CustomerRegisterDTO;
import com.Ecommerce.demo.Exception.User.AdminNotFoundException;
import com.Ecommerce.demo.Exception.User.CustomerNotFoundException;
import com.Ecommerce.demo.Mapper.UserMapper;
import com.Ecommerce.demo.Model.User.Admin;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.User.USER_TYPE;
import com.Ecommerce.demo.Model.User.User;
import com.Ecommerce.demo.Repository.UserRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LoginService extends BaseService {

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> loginCustomer(LoginRequest loginRequest) {

        Customer customer;
        try {
            customer=findCustomer(loginRequest.getEmail());
        }catch (CustomerNotFoundException ex){
            return new ResponseEntity<>("Invalid Email or Password",HttpStatus.UNAUTHORIZED);
        }
        System.err.println(loginRequest);

        if (!passwordEncoder.matches(loginRequest.getPassword(),customer.getPassword())){
            return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }
        LoggedInUser loggedInUser=userMapper.toLoggedInUser(customer);

        return ResponseEntity.ok(loggedInUser);
    }


    //Not sure if I should make two separate functions
    public ResponseEntity<?> loginAdmin(LoginRequest loginRequest){

        Admin admin;
        try{
            admin=findAdmin(loginRequest.getEmail());
        }catch (AdminNotFoundException ex){
            return new ResponseEntity<>("No such admin exists",HttpStatus.UNAUTHORIZED);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())){
            return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }
        LoggedInUser loggedInUser=userMapper.toLoggedInUser(admin);

        return ResponseEntity.ok(loggedInUser);
    }
}
