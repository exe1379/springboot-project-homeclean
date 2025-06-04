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
			String hashSalt = Hash.getSalt();
			String hashPassword = Hash.getHash(password, hashSalt);
			String token = UUID.randomUUID().toString();
			User user = new User();
			user.setUserName(username);
			user.setPasswordHash(hashPassword);
			user.setSalt(hashSalt);
			user.setEmail(email);
			user.setRole(Role.user);
			user.setCreatedDate(LocalDateTime.now());
			userRepository.save(user);
			
			String confirmEmail = "http://localhost:8080/api/email/confirm?token=" + token;
			emailService.sendEmail(token, confirmEmail);
	}

	@Override
	public void emailConfiration(String username) {
		
	}

}
