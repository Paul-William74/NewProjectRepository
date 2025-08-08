package com.Ecommerce.demo.Components;

import com.Ecommerce.demo.Events.AdminAnswerEvent;
import com.Ecommerce.demo.Events.Customer.CustomerRegisterEvent;
import com.Ecommerce.demo.Events.Customer.OTPEvent;
import com.Ecommerce.demo.Model.User.Admin;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationHandler {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final EmailSender emailSender;

    @Async
    public void publishSuccessfulUserRegistrationEmail(User user) {
        if (user instanceof Customer customer) {
            CustomerRegisterEvent customerRegisterEvent = new CustomerRegisterEvent(this,
                   customer, emailSender); //creating the welcoming user email event
            applicationEventPublisher.publishEvent(customerRegisterEvent); //trigger email sending to the customer to welcome them

        }else if (user instanceof Admin) {
            //send email to the admin
        }

    }

    @Async
    public void publishUserOTPVerificationEmail(User user,  String otp) {
        applicationEventPublisher.publishEvent(new OTPEvent(this, user, otp, emailSender));
    }

    @Async
    public void publishNotificationToCollaborators(List<Customer> customerList, String productName) {

        customerList.forEach(customer ->
                applicationEventPublisher.publishEvent(new AdminAnswerEvent(this, customer.getEmail(),
                        customer.getFirstName(), customer.getLastName(), productName, emailSender))
        ); // publish for each independently
    }

    @Async
    public void publishRestockedProductsFromWaitingList() {}

    @Async
    public void publishWishlistProductOnSale() {}

}
