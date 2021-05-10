package bookshelf.models.services;

import bookshelf.models.entities.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private String mailTo;
    @Value("${mail.from")
    private String mailFrom;
    private String subject;


    private final JavaMailSender javaMailSender;

    //TODO Здесь могут быьть ошибки
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMessageBuy(String messageText, String mailTo){
        subject = "Buying from Bookshelf shop";
        this.mailTo = mailTo;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(messageText);
        javaMailSender.send(message);
    }
}
