package com.Ecommerce.demo.Service.WaitingList;

import com.Ecommerce.demo.DTO.Product.WaitingList.WaitingWishListEntry;
import com.Ecommerce.demo.Mapper.WaitingWishListMapper;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.WaitingList.WaitingList;
import com.Ecommerce.demo.Repository.WatingList.WaitingListRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaitingListService extends BaseService {


    private final WaitingListRepo waitingListRepo;
    private final WaitingWishListMapper waitingWishListMapper;

    public ResponseEntity<?> addToWaitingList(Long customerId, Long productSizeId, String material) {

        MATERIAL materialWanted = MATERIAL.getMaterialFromLabel(material);

        Customer customer = findCustomer(customerId); // Find the customer by ID
        ProductSize productSize = findProductSize(productSizeId); // Find the product size by ID
        WaitingList waitingListEntry = new WaitingList(productSize, customer, materialWanted); // Create a new WaitingList entry

        try {
            WaitingList newEntry = this.waitingListRepo.save(waitingListEntry); //save the waiting list entry
            return ResponseEntity.ok(new Object());
        }catch (DataIntegrityViolationException ex) {

            String productName = productSize.getProduct().getName(); //get the product name
            return ResponseEntity.badRequest().body(productName + " With Size " + productSize.getSize() +
                    " is Being Monitored for Arrival.We Will Notify You As Soon As it Arrives" );
        }
    }

}
