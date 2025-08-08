package com.Ecommerce.demo.Events;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OTPEvent extends ApplicationEvent {

    private final String fistName;
    private final String lastName;
    private final String email;
    private final String OTP;

    public OTPEvent(Object source, String fistName, String lastName, String email, String OTP) {
        super(source);
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.OTP = OTP;
    }
}
