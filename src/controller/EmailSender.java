package controller;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class EmailSender {

    public static String sendEmail(String email, String name) {
        String senderEmail = "cunsoft.auto@gmail.com";
        String senderPassword = "bcndlaekyuotkosg";

        String recipientEmail = email;

        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";

       

        // Generate a random 6-digit code
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        String verificationCode = String.valueOf(code);
        
         String subject = "Forgot Password";
        String body = "Hi " + name + ",\n\n"
                + "We received a request to reset your password. Please use the following verification code to reset your password:\n\n"
                + "Verification Code: " + verificationCode + "\n\n"
                + "If you did not request a password reset, please ignore this email.\n\n"
                + "Best regards,\n"
                + "Your Coffee Shop";
        
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body + "\nVerification Code: " + verificationCode);

            Transport.send(message);

            System.out.println("Email sent successfully.");

            // Return the verification code
            return verificationCode;
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }

        return null;
    }
}
