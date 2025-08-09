package com.Ecommerce.demo.Events.Customer;

import com.Ecommerce.demo.Components.EmailSender;
import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.Review.ProductReview;
import com.Ecommerce.demo.Events.EmailSendingService;

import com.Ecommerce.demo.Model.Product.*;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import com.Ecommerce.demo.Util.Email.EmailContent.WishlistItemOnSpecial;
import org.springframework.beans.factory.xml.UtilNamespaceHandler;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public final class WishListItemOnSpecialEvent extends ApplicationEvent implements EmailSendingService {

    private final Formatter formatter;
    private final String emailContent;
    private final String email;
    private final EmailSender emailSender;

    public WishListItemOnSpecialEvent(Object source, EmailSender emailSender,
                                      Customer customer, ProductVariant productVariant,
                                      MATERIAL material, Formatter formatter) {
        super(source);
        this.emailContent =buildItemOnSaleMessageContent(material, customer, productVariant);
        this.emailSender = emailSender;
        this.email= customer.getEmail();
        this.formatter = formatter;
    }

    @Override
    public void sendEmail() {
        WorkEmail workEmail =  new WorkEmail("waltsperfumes@gmail.com", "hryt avjt whrf slgv");

        try {
            emailSender.sendEmail(workEmail, email, "Wishlist Item on Special", emailContent);
        }catch (Exception e) {

        }
    }


    private String buildItemOnSaleMessageContent(MATERIAL material, Customer customer, ProductVariant productVariant) {

        ProductSize productSize = productVariant.getProductSize(); //get the size
        Product product = productSize.getProduct(); //get the product
        String imgUrl = getImageUrlFromMaterial(productSize, material).getImgUrl(); //ge the image url

        String originalPrice = formatter.getFormattedPrice(product.getBasePrice()); //get the original price
        String salePrice = formatter.getFormattedPrice(product.getBasePrice() - (product.getBasePrice() * product.getDiscountPercentage()));
        String discountPercentage = String.valueOf(product.getDiscountPercentage() * 100); // re format the percentage


        String quntity = String.valueOf(productVariant.getQuantity()); //get the quantity that's left
        String sizeId = String.valueOf(productSize.getId()); //get the sizeId
        String size = String.valueOf(productSize.getSize()); //get the size literal

        return WishlistItemOnSpecial.buildWishlistSaleEmail(customer.getFirstName(),
                customer.getLastName(), imgUrl, material.getLabel(), size, sizeId, originalPrice,
                salePrice, discountPercentage, quntity,null);
    }


    private ProductImage getImageUrlFromMaterial(ProductSize productSize, MATERIAL material) {

        String name = productSize.getProduct().getName();
        Product product = productSize.getProduct();
        return product.getProductImages().stream()
                .filter(productImage ->  productImage.getMaterial() .equals(material))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(name + " With Size " + productSize.getSize() + " with material " + material
                +" Does not Exist"));
    }
}
