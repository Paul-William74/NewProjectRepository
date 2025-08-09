package com.Ecommerce.demo.Service.Address;

import com.Ecommerce.demo.DTO.Address.AddressRequestDTO;
import com.Ecommerce.demo.Exception.User.UserNotFoundException;
import com.Ecommerce.demo.Mapper.AddressMapper;
import com.Ecommerce.demo.Model.User.Address;
import com.Ecommerce.demo.Model.User.User;
import com.Ecommerce.demo.Repository.AddressRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService extends BaseService {

    private final AddressMapper addressMapper;
    private final AddressRepo addressRepo;

    /**
     * creates an address for a user. works for both admin and customer
     * @param user_id user that want to have an address
     * @param addressRequestDTO addressDTO holding the user information
     * @return return the registered address back
     */
    public ResponseEntity<?> createAddress(Long user_id, AddressRequestDTO addressRequestDTO) {

        User user = findUser(user_id); //try to look for the customer
        Address address = addressMapper.toAddress(addressRequestDTO);
        address.setUser(user); //set the user belonging to that address
        this.addressRepo.save(address); //save the address to the database

        return ResponseEntity.ok(addressRequestDTO);
    }

    /**
     * Edits an address for a user. Works for both admin and customer
     * @param user_id user that want to have an address
     * @param addressRequestDTO addressDTO holding the user information
     * @return return the updated address back
     */
    public ResponseEntity<?> editAddress(Long user_id,Long addressId, AddressRequestDTO addressRequestDTO) {

        User user=findUser(user_id);
        Optional<Address> DBAddress=addressRepo.findById(addressId);

        if(DBAddress.isEmpty()){
            return ResponseEntity.badRequest().body("Invalid Address");
        }

        Address address=DBAddress.get();
        //check if user owns the address
        if(address.getUser()!=user){
            ResponseEntity.badRequest().body("Cannot edit Address; Invalid User");
        }
        address.setStreet(addressRequestDTO.getStreet());
        address.setCity(addressRequestDTO.getCity());
        address.setProvince(address.getProvince());
        address.setPostalCode(address.getPostalCode());

        addressRepo.save(address);

        return ResponseEntity.ok(addressRequestDTO);
    }

    /**
     * Deletes an address for a user. Works for both admin and customer
     * @param user_id user that want to have an address
     * @param addressId address that is to be deleted
     * @return return if the deletion was a success
     */
    public ResponseEntity<?> deleteAddress(Long user_id, Long addressId) {

        User user;
        try{
            user=findUser(user_id);
        }catch (UserNotFoundException ex){
            return  ResponseEntity.badRequest().body("Invalid User");
        }

        Optional<Address> DBAddress =addressRepo.findById(addressId);
        if(DBAddress.isEmpty()){
            return  ResponseEntity.badRequest().body("Invalid Address");
        }

        Address address=DBAddress.get();
        if (address.getUser()!=user){
            return  ResponseEntity.badRequest().body("User cannot delete Address");
        }

        addressRepo.delete(address);

        return ResponseEntity.ok("Successfully deleted address");
    }


}


