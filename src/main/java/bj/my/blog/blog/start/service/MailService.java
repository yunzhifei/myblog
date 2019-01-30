package bj.my.blog.blog.start.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

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

    public void sendHtmlMail(String to, String subject, String content, String[] filepaths) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            //如果有附件就添加附件
            for (String filePath : filepaths) {
                FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
                String fileName = filePath.substring(filePath.lastIndexOf(File.separatorChar));
                mimeMessageHelper.addAttachment(fileName, fileSystemResource);
            }
            mailSender.send(message);
            logger.info("html 发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendTemplateMail(String to, String subject, String template, Map<String, Object> params, String[] filePaths) {
        Context context = new Context();
        //设置传入的参数值
        for (String key : params.keySet()) {
            context.setVariable(key, params.get(key));
        }
        //初始化模板引擎
        TemplateEngine templateEngine = new TemplateEngine();
        String content = templateEngine.process(template, context);
        sendHtmlMail(to, subject, content, filePaths);
    }

    //发送带附件的html模板文件
    public void sendAttachMail(String to, String subject, String template, Map<String, Object> params, String[] filePaths) {
        sendTemplateMail(to, template, subject, params, filePaths);
    }
}
