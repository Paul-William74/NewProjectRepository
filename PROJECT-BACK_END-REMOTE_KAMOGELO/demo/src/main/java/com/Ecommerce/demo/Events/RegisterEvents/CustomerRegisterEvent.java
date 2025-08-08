package com.Ecommerce.demo.Events.RegisterEvents;
import com.Ecommerce.demo.Components.EmailSender;
import com.Ecommerce.demo.Events.EmailSendingService;
import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import com.Ecommerce.demo.Util.Email.EmailContent.CustomerRegistration;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class CustomerRegisterEvent extends ApplicationEvent implements EmailSendingService {

    private final String email;
    private final String emailContent;
    private EmailSender emailSender;

    public CustomerRegisterEvent(Object context, String firstName, String lastName, String email, int loyaltyPoints) {
        super(context);
        this.email = email;
        this.emailContent = CustomerRegistration.getContent(firstName, lastName,loyaltyPoints);
        emailSender = new EmailSender();
    }


    @Override
    public void sendEmail() {

        try {

            emailSender.sendEmail(new WorkEmail("waltsperfumes@gmail.com", "hryt avjt whrf slgv"),
                    email, "Welcome To Our Website", emailContent);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
