package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.RegisterRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserRegisterService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
public class RegisterRestController {
	
	@Autowired
	private UserRegisterService userRegisterService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<Void>> register(@RequestBody RegisterRequest request){
		userRegisterService.addUser(request.getUsername(), request.getPassword(), request.getEmail());
		return ResponseEntity.ok(ApiResponse.success("註冊成功", null));
	}
}
