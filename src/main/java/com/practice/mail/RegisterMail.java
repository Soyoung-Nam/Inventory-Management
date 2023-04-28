package com.practice.mail;

import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/*
@Service
public class RegisterMail implements MainServiceInter{

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MemberService memberService;

    @Override
    public MimeMessage createMessage(String ToEmail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, ToEmail); //보내는 대상

        String id = memberService.getIdByEmail(ToEmail);

        message.setSubject("재고관리 아이디찾기"); //제목
        String msg = "";
        msg += "<div>";
        msg += "<p> 안녕하세요.</p>";
        msg += "<p> 재고관리 아이디입니다.</p>";
        msg += "<br>";
        msg += "<strong>" + id + "</strong>" ;
        msg += "<br>";
        msg += "</div>";
        message.setText(msg, "utf-8", "html");//내용, charset 타입, subtype
        //보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("soyoung0150@naver.com", "inventory_Admin"));

        return message;
    }

    @Override
    public void sendSimpleMessage(String ToEmail) throws Exception {
        MimeMessage message = createMessage(ToEmail); //메일 발송

        try { //예외처리
            javaMailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
 */
