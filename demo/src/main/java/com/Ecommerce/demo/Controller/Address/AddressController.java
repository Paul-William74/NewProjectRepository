package com.Ecommerce.demo.Controller.Address;

import com.Ecommerce.demo.DTO.Address.AddressRequestDTO;
import com.Ecommerce.demo.Service.Address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @PostMapping("/createAddress/{user_id}")
    public ResponseEntity<?> createAddress(
            @PathVariable final Long user_id,
            @RequestBody final AddressRequestDTO addressRequest) {
        return this.addressService.createAddress(user_id, addressRequest);
    }
    @PutMapping("/editAddress/{user_id}/{address_id}")
    public ResponseEntity<?> editAddress(
            @PathVariable final Long user_id,
            @PathVariable final Long address_id,
            @RequestBody final AddressRequestDTO addressRequest) {
        return this.addressService.editAddress(user_id,address_id, addressRequest);
    }

    @DeleteMapping("/deleteAddress/{user_id}/{address_Id}")
    public ResponseEntity<?> deleteAddress(
            @PathVariable final Long user_id,
            @PathVariable final Long address_Id) {
        return this.addressService.deleteAddress(user_id,address_Id);
    }





}
