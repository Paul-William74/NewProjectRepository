package com.Ecommerce.demo.Events.Customer;

import com.Ecommerce.demo.Components.EmailSender;
import com.Ecommerce.demo.Events.EmailSendingService;
import com.Ecommerce.demo.Exception.ImageForMaterialNotFound;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductImage;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import org.springframework.context.ApplicationEvent;

import java.util.logging.Logger;

import static com.Ecommerce.demo.Util.Email.EmailContent.WaitlistConfirmation.buildWaitlistEmail;

public final class SuccessfulWaitingListAdding extends ApplicationEvent implements EmailSendingService {

    private final Logger logger  = Logger.getLogger(SuccessfulWaitingListAdding.class.getName());
    private final String emailContent;
    private final EmailSender emailSender;
    private final String email;

    public SuccessfulWaitingListAdding(Object source, EmailSender emailSender, Customer customer, ProductSize productSize, MATERIAL material) {
        super(source);

        emailContent = setEmailContent(customer, productSize, material);
        this.email = customer.getEmail();
        this.emailSender = emailSender;
    }


    @Override
    public void sendEmail() {
        logger.info("Attempting to send wishlist registration email to: " + email);
        try {
            WorkEmail workEmail = new WorkEmail("waltsperfumes@gmail.com", "hryt avjt whrf slgv");
            emailSender.sendEmail(workEmail, email, "WishList Registration", emailContent);
            logger.info("Email sent successfully to: " + email);
        } catch (Exception e) {
            logger.severe("Failed to send email to: " + email + ". Error: " + e.getMessage());
        }
    }


    private String getImgUrlFromMaterial(ProductSize productSize, String material) {
        return productSize.getProduct().getProductImages().stream()
                .filter(image -> image.getMaterial().getLabel().equalsIgnoreCase(material))
                .findFirst()
                .map(ProductImage::getImgUrl)
                .orElse(null);
    }


    private String setEmailContent(Customer customer, ProductSize productSize, MATERIAL material) {

        String imgUrl = getImgUrlFromMaterial(productSize, material.getLabel());

        if(imgUrl != null) {
             return buildWaitlistEmail(customer.getFirstName(),
                    customer.getLastName(),
                    productSize.getProduct().getName(), imgUrl,
                    String.valueOf(productSize.getSize()),
                    material.getLabel(), "");
        }else {

            Product product = productSize.getProduct();
            String materialLabel = material.getLabel();
            throw new ImageForMaterialNotFound("Image for: " + product.getName() + " with Material: " + materialLabel
            + " Not Found.Email Sending Was Gracefully Terminated");
        }
    }
}
