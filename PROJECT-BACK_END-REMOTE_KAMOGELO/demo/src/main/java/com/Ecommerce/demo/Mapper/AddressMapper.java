package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.DTO.Address.AddressRequestDTO;
import com.Ecommerce.demo.Model.User.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressRequestDTO addressRequestDTO);
}
