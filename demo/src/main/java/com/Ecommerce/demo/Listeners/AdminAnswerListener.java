package com.Ecommerce.demo.Listeners;

import com.Ecommerce.demo.Events.AdminAnswerEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class AdminAnswerListener {

    @EventListener
    public void publishAdminAnswerNotification(AdminAnswerEvent adminAnswerEvent) {
        adminAnswerEvent.sendEmail();
    }
}
