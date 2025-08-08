package com.Ecommerce.demo.Listeners.Customer;

import com.Ecommerce.demo.Events.Customer.CustomerRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class CustomerRegistrationListener {

    @EventListener
    public void handleSendingRegisteredEmail(CustomerRegisterEvent customerRegisterEvent) {
        customerRegisterEvent.sendEmail();
    }
}
