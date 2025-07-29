package com.Ecommerce.demo.Listeners;

import com.Ecommerce.demo.Events.RegisterEvents.CustomerRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class CustomerRegistrationListener {

    @EventListener
    public void handleSendingRegisteredEmail(CustomerRegisterEvent customerRegisterEvent) {
        customerRegisterEvent.sendEmail();
    }
}
