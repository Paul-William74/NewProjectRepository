package com.Ecommerce.demo.Events.Customer;
import com.Ecommerce.demo.Components.EmailSender;
import com.Ecommerce.demo.Events.EmailSendingService;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Model.User.User;
import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import com.Ecommerce.demo.Util.Email.EmailContent.CustomerForgotPassword;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.logging.Logger;

@Getter
public final class OTPEvent extends ApplicationEvent implements EmailSendingService {

    private final String emailContent;
    private final EmailSender emailSender;
    private final String email;
    private final Logger logger = Logger.getLogger(OTPEvent.class.getName());


    public OTPEvent(Object source, User user, String OTP, EmailSender emailSender) {
        super(source);
        this.emailContent = CustomerForgotPassword.buildOtpEmail(user.getFirstName(), user.getLastName(), OTP);
        this.emailSender = emailSender;
        this.email = user.getEmail();
    }

    @Override
    public void sendEmail() {
        try  {

            WorkEmail workEmail = new WorkEmail("waltsperfumes@gmail.com", "hryt avjt whrf slgv");
            emailSender.sendEmail(workEmail, email, "One Time Password Verification", emailContent);
        } catch (Exception ex) {
            logger.severe("Failed to send OTP email: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
