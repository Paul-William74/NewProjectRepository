package com.Ecommerce.demo.Listeners.Customer;

import com.Ecommerce.demo.Events.Customer.BackInStockEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class BackInStockListener {

    @EventListener
    public void handleBackInStockEvent(BackInStockEvent backInStockEvent) {
        backInStockEvent.sendEmail();
    }
}
