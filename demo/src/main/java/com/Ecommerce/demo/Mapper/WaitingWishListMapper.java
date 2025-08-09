package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.WaitingList.WaitingWishListEntry;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductImage;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.WaitingList.WaitingList;
import com.Ecommerce.demo.Model.Wishlist.WishList;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class WaitingWishListMapper {

    @Autowired private Formatter formatter;



    public WaitingWishListEntry toWaitingWishListEntry(WaitingList waitingList) {

        double price = waitingList.getProductSize().getProduct().getBasePrice(); //get the price of the product
        Product product = waitingList.getProductSize().getProduct(); //get the product from the waiting list
        String[] imageUrls = getImageUrls(product.getProductImages()); //get the images

        return new WaitingWishListEntry(
            waitingList.getId(),
            imageUrls,
            waitingList.getProductSize().getProduct().getName(),
                formatter.getFormattedPrice(price),
                "Out of Stock",
                waitingList.getProductSize().getSize()
        );
    }

    public WaitingWishListEntry toWaitingWishListEntry(WishList wishList) {

        double price = wishList.getProductSize().getProduct().getBasePrice(); //get the price of the product
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

    public List<WaitingWishListEntry> toWaitingWishListEntries(List<WaitingList> waitingLists) {
        return waitingLists.stream()
                .map(this::toWaitingWishListEntry)
                .toList();
    }

    private String[] getImageUrls(List<ProductImage> productMaterials) {
      return productMaterials.stream()
              .map(ProductImage::getImgUrl) //get the image url from the product image
              .toArray(String[]::new); //convert to array
    }
}
