package com.Ecommerce.demo.Components;

import com.Ecommerce.demo.Events.AdminAnswerEvent;
import com.Ecommerce.demo.Events.RegisterEvents.CustomerRegisterEvent;
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

    @Async
    public void publishSuccessfulUserRegistrationEmail(User user) {
        if (user instanceof Customer) {
            CustomerRegisterEvent customerRegisterEvent = new CustomerRegisterEvent(this,
                    user.getFirstName(), user.getLastName(), user.getEmail(), 150); //creating the welcoming user email event
            applicationEventPublisher.publishEvent(customerRegisterEvent); //trigger email sending to the customer to welcome them

        }else if (user instanceof Admin) {
            //send email to the admin
        }

    }

    @Async
    public void publishNotificationToCollaborators(List<Customer> customerList, String productName) {

        customerList.forEach(customer ->
                applicationEventPublisher.publishEvent(new AdminAnswerEvent(this, customer.getEmail(),
                        customer.getFirstName(), customer.getLastName(), productName ))
        ); // publish for each independently
    }

    @Async
    public void publishRestockedProductsFromWaitingList() {}

    @Async
    public void publishWishlistProductOnSale() {}

}
