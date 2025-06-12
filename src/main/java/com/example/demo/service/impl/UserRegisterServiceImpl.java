package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserRegisterService;
import com.example.demo.util.Hash;
@Service
public class UserRegisterServiceImpl implements UserRegisterService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public void addUser(String username, String password, String email) {
			
		   if(userRepository.findByUserName(username) != null) {
             throw new UserAlreadyExistException("使用者已存在");
		 	}
		   if(userRepository.findByEmail(email) != null) {
             throw new UserAlreadyExistException("Email 已被使用");
		 	}
			String hashSalt = Hash.getSalt();
			String hashPassword = Hash.getHash(password, hashSalt);
			String token = UUID.randomUUID().toString();
			User user = new User();
			user.setUserName(username);
			user.setPasswordHash(hashPassword);
			user.setSalt(hashSalt);
			user.setEmail(email);
			user.setName("");
			user.setRole(Role.USER);
			user.setPhoneNumber("");
			user.setEmailToken(token);
			user.setCreatedDate(LocalDateTime.now());
			userRepository.saveAndFlush(user);
			System.out.println("使用者帳號: " + username);
			System.out.println("產生的 token: " + token);
			System.out.println("User 對象 email_token 屬性值: " + user.getEmailToken());
			String confirmEmail = "http://localhost:5173/verify-email?token=" + token;
			emailService.sendEmail(user.getEmail(), confirmEmail);
	}

		@Override
		public void emailConfirmation(String token) {
			User user = userRepository.findByEmailToken(token);
			if(user == null) {
				throw new IllegalArgumentException("無效的驗證連結");
			}
			user.setEmailVerified(true);
			user.setEmailToken(null);
			userRepository.save(user);
		}

}
