package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestEmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/email")
    public String sendTestEmail(@RequestParam String to) {
        String confirmUrl = "http://localhost:8081/confirm?token=123456";
        emailService.sendEmail(to, confirmUrl);
        return "已發送測試信件至：" + to;
    }
}
