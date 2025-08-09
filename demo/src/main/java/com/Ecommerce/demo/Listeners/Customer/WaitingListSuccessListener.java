package com.Ecommerce.demo.Listeners.Customer;

import com.Ecommerce.demo.Events.Customer.SuccessfulWaitingListAdding;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class WaitingListSuccessListener {

    @EventListener
    public void publishEvent(SuccessfulWaitingListAdding successfulWaitingListAdding) {
        successfulWaitingListAdding.sendEmail();
    }
}
