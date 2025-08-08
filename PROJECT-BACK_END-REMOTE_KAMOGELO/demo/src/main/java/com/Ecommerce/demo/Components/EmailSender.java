package com.Ecommerce.demo.Components;

import com.Ecommerce.demo.Model.WorkEmail.WorkEmail;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public final class EmailSender {

    /**
     * Sends an email to a recipient.
     * @param workEmail User[company email] sending the email.
     * @param recipient Email of the recipient.
     * @param subject Subject of the email.
     * @param content HTML content of the email.
     * @throws MessagingException If an error occurs while sending.
     * @throws UnsupportedEncodingException If the encoding is unsupported.
     */
    public void sendEmail(WorkEmail workEmail, String recipient, String subject, String content)
            throws MessagingException, UnsupportedEncodingException {
        // Sender email credentials
        final String senderEmail = workEmail.getEmail();
        final String senderPassword = workEmail.getAppPassword(); // You can use admin.getEmailPassword() if necessary

        // SMTP server settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a Session with Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Compose Email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        // Set subject with UTF-8 encoding
        message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));

        // Ensure the HTML content is UTF-8 encoded

        message.setContent(content, "text/html; charset=UTF-8");

        // Send Email
        Transport.send(message);
    }
}


