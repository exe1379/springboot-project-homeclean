package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.ApiResponse;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class EmailConfirmController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/confirm")
	public ResponseEntity<ApiResponse<String>> confirmEmail(@RequestParam String token){
		System.out.println("🔍 後端收到的 token: " + token); 
		User user = userRepository.findByEmailToken(token);
		if(user == null) {
		return ResponseEntity.badRequest()
			.body(ApiResponse.error(402, "無效token,驗證失敗"));
		}
		user.setEmailVerified(true);
		user.setEmailToken(null);
		userRepository.save(user);
		
		return ResponseEntity.ok(ApiResponse.success("email 驗證成功",null));
	}
}
