package com.Ecommerce.demo.Service.Wishlist;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.WaitingList.WaitingWishListEntry;
import com.Ecommerce.demo.Mapper.WaitingWishListMapper;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductImage;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.Wishlist.WishList;
import com.Ecommerce.demo.Repository.Product.ProductPriceRepo;
import com.Ecommerce.demo.Repository.Wishlist.WishlistRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService extends BaseService {


    private final WishlistRepo wishlistRepo;
    private WaitingWishListMapper waitingWishListMapper;
    private final ProductPriceRepo productPriceRepo;
    private final Formatter formatter;

    public ResponseEntity<?> addToWishlist(final Long productSizeId, Long customerId, String material) {

        MATERIAL materialWanted = MATERIAL.getMaterialFromLabel(material);
        Customer customer = this.findCustomer(customerId); //find the customer by ID
        ProductSize productSize = this.findProductSize(productSizeId); //find the product size by ID

        WishList wishListEntry = new WishList(productSize, customer, materialWanted);

        try {

            WishList newEntry = this.wishlistRepo.save(wishListEntry);

            double price = 0.0;
            if(productSize.getProduct().isOnDiscount())
                price = productSize.getDiscountAmount();
            else
                price = productPriceRepo.findByProduct(productSize.getProduct()).get().getBasePrice();

            WaitingWishListEntry dtoEntry = new WaitingWishListEntry(
                    newEntry.getId(),
                    null,
                    productSize.getProduct().getName(),
                    formatter.getFormattedPrice(price),
                    productSize.getProduct().getDescription(),
                    productSize.getSize()
            );

            return ResponseEntity.ok(dtoEntry);
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









    public WaitingWishListEntry toWaitingWishListEntry(WishList wishList) {

        double price = 0.0;
        if(wishList.getProductSize().isOnDiscount())
            price = wishList.getProductSize().getDiscountAmount(); //if the product is on discount
        else
            price = productPriceRepo.findByProduct(wishList.getProductSize().
                            getProduct()).
                                get().getBasePrice(); //no error since product size can only exist if s product exists ;)

        Product product = wishList.getProductSize().getProduct(); //get the product from the waiting list
        String[] imageUrls = getImageUrls(product.getProductImages()); //get the images

        return new WaitingWishListEntry(
                wishList.getId(),
                imageUrls,
                wishList.getProductSize().getProduct().getName(),
                formatter.getFormattedPrice(price),
                "Out of Stock",
                wishList.getProductSize().getSize()
        );
    }


    private String[] getImageUrls(List<ProductImage> productMaterials) {
        return productMaterials.stream()
                .map(ProductImage::getImgUrl) //get the image url from the product image
                .toArray(String[]::new); //convert to array
    }
}
