package dasturlash.uz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;


    public void send(String toAccount, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAccount);
        msg.setFrom(fromEmail);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }

}
