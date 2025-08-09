package com.Ecommerce.demo.Components.Publisher;
import com.Ecommerce.demo.Components.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public abstract class Publisher {

    @Autowired
    protected ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    protected EmailSender emailSender;

}
