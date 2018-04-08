package com.duan.blogos.manager;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created on 2018/4/8.
 *
 * @author DuanJiaNing
 */
@Component
public class MailManager {

    private final JavaMailSenderImpl mailSender;

    public MailManager() {
        mailSender = new JavaMailSenderImpl();

        //指定用来发送Email的邮件服务器主机名
        mailSender.setHost("smtp.qq.com");

        //默认端口，标准的SMTP端口
        mailSender.setPort(465);

        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");

        mailSender.setJavaMailProperties(properties);
    }

    public JavaMailSenderImpl getMailSender(String username, String password) {
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }
}
