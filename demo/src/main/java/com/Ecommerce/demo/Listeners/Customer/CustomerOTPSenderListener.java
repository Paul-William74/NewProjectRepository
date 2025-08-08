package com.Ecommerce.demo.Listeners.Customer;

import com.Ecommerce.demo.Events.Customer.OTPEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class CustomerOTPSenderListener {

    @EventListener
    public void send_OPT_ToUserViaEmail(OTPEvent otpEvent) {
        otpEvent.sendEmail();
    }
}
