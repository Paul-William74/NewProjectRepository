package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.DTO.LogIn.LoggedInUser;
import com.Ecommerce.demo.DTO.Register.AdminRegisterDTO;
import com.Ecommerce.demo.DTO.Register.CustomerRegisterDTO;
import com.Ecommerce.demo.Exception.User.InvalidUserTypeException;
import com.Ecommerce.demo.Model.User.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AddressMapper addressMapper;


    @Mapping(source = "password", target = "password", qualifiedByName = "hashedPassword")
    public Customer toCustomerWishAddress(CustomerRegisterDTO customerRegisterDTO) {

        Customer customer = mapDefaultCustomer(customerRegisterDTO);
        if(customerRegisterDTO.getAddress() != null) {
            Address address = addressMapper.toAddress(customerRegisterDTO.getAddress());
            customer.addAddress(address);
        }
        return customer;
    }

    @Mapping(source = "password", target = "password", qualifiedByName = "hashedPassword")
    public abstract Customer mapDefaultCustomer(CustomerRegisterDTO customerRegisterDTO);


    @Mapping(source = "password", target = "password", qualifiedByName = "hashedPassword")
    public abstract Admin toAdmin(AdminRegisterDTO adminRegisterDTO);


    @Named("hashedPassword")
    String mapPassword(String password) {
        return this.passwordEncoder.encode(password);
    }
    USER_TYPE mapUserType(String userType) throws InvalidUserTypeException {
        return USER_TYPE.getUserType(userType);
    }

    public abstract LoggedInUser toLoggedInUser(User user);
}
