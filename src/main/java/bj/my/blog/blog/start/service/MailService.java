package bj.my.blog.blog.start.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private Logger logger = LoggerFactory.getLogger(MailService.class);
    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void sendMailSimple(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        logger.info(from);
        try {
            mailSender.send(message);

            logger.info("邮件已经发送！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
