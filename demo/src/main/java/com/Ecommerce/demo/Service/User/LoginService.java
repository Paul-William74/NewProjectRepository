package com.Ecommerce.demo.Service.User;
import com.Ecommerce.demo.DTO.LogIn.LoggedInUser;
import com.Ecommerce.demo.DTO.LogIn.LoginRequest;
import com.Ecommerce.demo.Exception.User.CustomerNotFoundException;
import com.Ecommerce.demo.Mapper.UserMapper;
import com.Ecommerce.demo.Model.User.User;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class LoginService extends BaseService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(LoginRequest loginRequest) {

        User user;
        try {
            user=findUser(loginRequest.getEmail());
        }catch (CustomerNotFoundException ex) {
            return new ResponseEntity<>("Invalid Email or Password", HttpStatus.BAD_REQUEST);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
        }
        LoggedInUser loggedInUser=userMapper.toLoggedInUser(user);

        return ResponseEntity.ok(loggedInUser);
    }


}
