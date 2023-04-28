package com.practice.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/*
@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername();
        javaMailSender.setPassword();
        javaMailSender.setPort();
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp"); //프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); //smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); //smtp strattles 사용
        properties.setProperty("mail.debug", "true"); //디버그 사용
        properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com"); //ssl 인증 서버는 smtp.naver.com
        properties.setProperty("mail.smtp.ssl.enable", "true"); //ssl 사용
        return properties;
    }
}
 */
