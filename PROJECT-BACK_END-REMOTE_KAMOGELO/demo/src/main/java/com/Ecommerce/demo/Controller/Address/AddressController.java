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




}
