package com.Ecommerce.demo.Service.Wishlist;


import com.Ecommerce.demo.DTO.Product.WaitingList.WaitingWishListEntry;
import com.Ecommerce.demo.Mapper.WaitingWishListMapper;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.Wishlist.WishList;
import com.Ecommerce.demo.Repository.Wishlist.WishlistRepo;
import com.Ecommerce.demo.Service.BaseService;
import com.Ecommerce.demo.Util.Email.EmailContent.CustomerForgotPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService extends BaseService {


    private final WishlistRepo wishlistRepo;
    private WaitingWishListMapper waitingWishListMapper;

    public ResponseEntity<?> addToWishlist(final Long productSizeId, Long customerId, String material) {

        MATERIAL materialWanted = MATERIAL.getMaterialFromLabel(material);
        Customer customer = this.findCustomer(customerId); //find the customer by ID
        ProductSize productSize = this.findProductSize(productSizeId); //find the product size by ID

        WishList wishListEntry = new WishList(productSize, customer, materialWanted);

        try {

            WishList newEntry = this.wishlistRepo.save(wishListEntry);

            WaitingWishListEntry dtoEntry = this.waitingWishListMapper.toWaitingWishListEntry(newEntry); // map the new WishList entry to a DTO
            return ResponseEntity.ofNullable(dtoEntry);
        }catch (DataIntegrityViolationException e) {
            Product product = productSize.getProduct(); //get the product from the product size
            return ResponseEntity.badRequest().body(product.getName() + " Has already been added to wishlist");
        }
    }

    public ResponseEntity<?> removeFromWishlist(final Long productSizeId, Long customerId, String material) {

        MATERIAL materialWanted = MATERIAL.getMaterialFromLabel(material);
        Customer customer = this.findCustomer(customerId); //find the customer by ID
        ProductSize productSize = this.findProductSize(productSizeId); //find the product size by ID

        WishList wishListEntry = this.wishlistRepo.findByProductSizeAndCustomerAndMaterial(productSize, customer, materialWanted)
                .orElseThrow(() -> new RuntimeException("Wishlist entry not found"));

        long wishListId = wishListEntry.getId(); //get the ID of the wishlist entry
        this.wishlistRepo.delete(wishListEntry); //delete the wishlist entry

        return ResponseEntity.ok(wishListId); //return the id of the deleted wishlist entry
    }
}
