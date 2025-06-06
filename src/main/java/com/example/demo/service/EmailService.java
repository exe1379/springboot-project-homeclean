package com.example.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired	
    private JavaMailSender mailSender;
    @Value("${spring.mail.from-address}")
    private String fromAddress;
    
    public void sendEmail(String to, String confirmUrl) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(to);
            helper.setSubject("會員註冊確認信");
            helper.setText("請點選以下連結進行帳號驗證：\n" + confirmUrl, false);
            helper.setFrom(fromAddress);

            mailSender.send(message);
            System.out.println("信件已寄出：" + to);

        } catch (MessagingException e) {
            System.out.println("寄信失敗：" + e.getMessage());
        }
    }
}
