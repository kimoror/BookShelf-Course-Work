package bookshelf.models.services;

import bookshelf.aspect.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmailService {
    @Value("${mail.from")
    private String mailFrom;
    private String subject;


    private final JavaMailSender javaMailSender;

    //TODO Здесь могут быьть ошибки
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    @Transactional
    @Loggable
    public void sendMessageBuy(String messageText, String mailTo){
        subject = "Buying from Bookshelf shop";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(messageText);
        javaMailSender.send(message);
    }

    
}
