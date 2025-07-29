package com.Ecommerce.demo.Service.Address;

import com.Ecommerce.demo.DTO.Address.AddressRequestDTO;
import com.Ecommerce.demo.Mapper.AddressMapper;
import com.Ecommerce.demo.Model.User.Address;
import com.Ecommerce.demo.Model.User.User;
import com.Ecommerce.demo.Repository.AddressRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService extends BaseService {


    private final AddressRepo addressRepo;
    private final AddressMapper addressMapper;

    /**
     * creates an address for a user. works for both admin and customer
     * @param user_id user that want to have an address
     * @param addressRequestDTO addressDTO holding the user information
     * @return return the registered address back
     */
    public ResponseEntity<?> createAddress(Long user_id, AddressRequestDTO addressRequestDTO) {

        User user = findUser(user_id); //try to look for the customer
        Address address = this.addressMapper.toAddress(addressRequestDTO);// create the address form the attributes
        address.setUser(user); //set the user belonging to that address
        this.addressRepo.save(address); //save the address to the database

        return ResponseEntity.ok(addressRequestDTO);
    }


    public ResponseEntity<?> updateAddress(Long user_id, Object o) {
        return null;
    }

    public ResponseEntity<?> deleteAddress(Long user_id, Object o) {
        return null;
    }
}
