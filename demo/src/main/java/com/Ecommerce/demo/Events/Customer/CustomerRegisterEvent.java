package com.Ecommerce.demo.Events.Customer;
import com.Ecommerce.demo.Components.EmailSender;
import com.Ecommerce.demo.Events.EmailSendingService;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import com.Ecommerce.demo.Util.Email.EmailContent.CustomerRegistration;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class CustomerRegisterEvent extends ApplicationEvent implements EmailSendingService {

    private final String emailContent;
    private EmailSender emailSender;
    private final String email;

    public CustomerRegisterEvent(Object context, Customer customer, EmailSender emailSender) {
        super(context);
        this.email = customer.getEmail();
        this.emailContent = CustomerRegistration.buildWelcomeEmail(customer.getFirstName(), customer.getLastName(), customer.getLoyaltyPoints());
        this.emailSender = emailSender;
    }


    @Override
    public void sendEmail() {

        try {
            WorkEmail workEmail =  new WorkEmail("waltsperfumes@gmail.com", "hryt avjt whrf slgv");
            emailSender.sendEmail(workEmail,
                    email, "Welcome To Our Website", emailContent);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
