package com.Ecommerce.demo.Service.User.Admin;


import com.Ecommerce.demo.Components.Publisher.WishlistPublisher;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductPrice;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Admin;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.WaitingList.WaitingList;
import com.Ecommerce.demo.Model.Wishlist.WishList;
import com.Ecommerce.demo.Repository.Product.ProductPriceRepo;
import com.Ecommerce.demo.Repository.Wishlist.WishlistRepo;
import com.Ecommerce.demo.Service.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminService extends BaseService {

    private final WishlistRepo wishlistRepo;
    private final WishlistPublisher wishlistPublisher;
    private final ProductPriceRepo productPriceRepo;


    @Transactional
    public ResponseEntity<?> placeProductSizeOnSale(Long admin_id, List<Long> productSize_ids, Double percentOff) {

        Admin admin = findAdmin(admin_id); //check if the admin is available;

        Product product = null;
        String material = "";
        for(Long productSizeId: productSize_ids) {

            ProductSize productSize = findProductSize(productSizeId);

            if (product == null)
                product = productSize.getProduct();

            Optional<ProductPrice> productPrice = productPriceRepo.findByProduct(productSize.getProduct());

            if (productPrice.isEmpty())
                return ResponseEntity.badRequest().body("An Error Occurred");

            if (material.equals(""))
                material = productSize.getMaterial().getLabel();

            //notify customers that want this product that its on sale
            notifyCustomersWithWishlistItemOnSale(productSize, percentOff, productPrice.get());

        }
        return ResponseEntity.ok("Product '" + product.getName() + "' has begun a sale on all its "
                + material + " sizes");
    }

    @Transactional
    public ResponseEntity<?> removeProductSizeOnSale(Long admin_id, Long productSize_id) {

        Admin admin = findAdmin(admin_id);
        ProductSize productSize = findProductSize(productSize_id);

        if(productSize.isOnDiscount()) {
            productSize.setOnDiscount(false); //set the discount off
            productSize.setDiscountAmount(0.0); //set the discount amount to 0
            this.productSizeRepo.save(productSize); //set the product size to the new changes

            return ResponseEntity.ok("Sale for: " + productSize.getProduct().getName() + " size " + productSize.getSize() + " with material: "
                    + productSize.getMaterial().getLabel() + " is Over");
        }

        return ResponseEntity.badRequest().body("An Error Occurred");
    }




    private void notifyCustomersWithWishlistItemOnSale(ProductSize productSize, Double percentOff, ProductPrice productPrice) {

        List<WishList> wishListsEntries = productSize.getWishListsEntries();

        int batchLimit = 100;
        int totalEntries = wishListsEntries.size();
        int startIndex = 0;

        while (startIndex < totalEntries) {
            int endIndex = Math.min(startIndex + batchLimit, totalEntries);
            List<WishList> batch = wishListsEntries.subList(startIndex, endIndex);

            for (WishList wishListEntry : batch) {
                Customer customer = wishListEntry.getCustomer();
                wishlistPublisher.publishWishlistProductOnSale(customer, productSize, productSize.getMaterial(), productPrice);
            }
            startIndex += batchLimit;
        }
        //send emails to n number of customers in batches to reduce overhead
        productSize.setDiscountAmount(productPrice.getBasePrice()  * (1 - percentOff / 100)); //set the discount amount
        productSize.setOnDiscount(true); //set it to be on sale
        productSizeRepo.save(productSize);

    }
}
