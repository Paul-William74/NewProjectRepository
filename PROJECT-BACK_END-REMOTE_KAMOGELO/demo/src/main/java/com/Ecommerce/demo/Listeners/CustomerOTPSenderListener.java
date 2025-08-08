package com.Ecommerce.demo.Listeners;

import com.Ecommerce.demo.Events.OTPEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class CustomerOTPSenderListener {

    @EventListener
    public void send_OPT_ToUserViaEmail(OTPEvent otpEvent) {

    }
}
