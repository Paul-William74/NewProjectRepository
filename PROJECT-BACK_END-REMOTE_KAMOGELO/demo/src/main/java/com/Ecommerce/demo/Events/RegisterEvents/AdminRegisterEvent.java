package com.Ecommerce.demo.Events.RegisterEvents;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public final class AdminRegisterEvent extends ApplicationEvent {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String temporaryPassword;

    public AdminRegisterEvent(Object source, String firstName, String lastName, String email, String temporaryPassword) {
        super(source);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.temporaryPassword = temporaryPassword;
    }
}
