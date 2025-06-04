package com.example.demo.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    // Google應用程式密碼
    private final String googleAppPassword = "你的應用程式密碼";

    // 寄件者的 Gmail 帳號
    private final String from = "你的Gmail帳號@gmail.com";

    public void sendEmail(String to, String confirmUrl) {
        String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, googleAppPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("會員註冊確認信");
            message.setText("請點選以下連結進行帳號驗證：\n" + confirmUrl);

            Transport.send(message);
            System.out.println("信件已寄出：" + to);
        } catch (MessagingException e) {
            System.out.println("寄信失敗：" + e.getMessage());
        }
    }
}
