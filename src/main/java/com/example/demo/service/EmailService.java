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
            helper.setText("""
            	    <p>您好，感謝註冊。</p>
            	    <p>請點擊以下連結完成 Email 驗證：</p>
            	    <p><a href="%s">%s</a></p>
            	    <p>如果您沒有申請註冊，請忽略此信。</p>
            	""".formatted(confirmUrl, confirmUrl), true); 
            helper.setFrom(fromAddress);

            mailSender.send(message);
            System.out.println("信件已寄出：" + to);

        } catch (MessagingException e) {
            System.out.println("寄信失敗：" + e.getMessage());
        }
    }
}
