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
                                      Customer customer,
                                      MATERIAL material, Formatter formatter, ProductPrice productPrice) {
        super(source);
        this.emailContent = "";
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
}
