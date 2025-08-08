package com.Ecommerce.demo.Controller.User;


import com.Ecommerce.demo.DTO.LogIn.LoginRequest;
import com.Ecommerce.demo.Service.User.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logins")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("login-user")
    public ResponseEntity<?> login(@Valid @RequestBody final LoginRequest loginRequest) {
        return this.loginService.login(loginRequest);
    }

}
