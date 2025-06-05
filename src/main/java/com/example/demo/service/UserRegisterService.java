package com.example.demo.service;

public interface UserRegisterService {
	
	void addUser(String username, String password, String email);
	
	void emailConfirmation(String token);
}
