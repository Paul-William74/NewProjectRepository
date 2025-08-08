package com.Ecommerce.demo.Events;

import com.Ecommerce.demo.Components.EmailSender;
import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import com.Ecommerce.demo.Util.Email.EmailContent.AdminQuestionAnswer;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class AdminAnswerEvent extends ApplicationEvent implements EmailSendingService {

    private final String email;
    private final String emailContent;
    private final EmailSender emailSender;

    public AdminAnswerEvent(Object source, String email, String firstName, String lastName, String productName, EmailSender emailSender) {
        super(source);
        this.email = email;
        emailContent = AdminQuestionAnswer.getAdminAnswerNotification(firstName, lastName, productName);
        this.emailSender = emailSender;;
    }


    @Override
    public void sendEmail() {

        try {
            emailSender.sendEmail(new WorkEmail("waltsperfumes@gmail.com", "hryt avjt whrf slgv"),
                    email, "Admin has responded", emailContent);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
