package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CertException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.CertService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
public class RestLoginController {
	
	@Autowired
	private CertService certService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Void>> login(@RequestParam String username,@RequestParam String password, HttpSession session){
		   try {
			   UserCert cert = certService.getCert(username, password);
			   session.setAttribute("UserCert", cert);
			   session.setAttribute("role", cert.getRole());
			   return ResponseEntity.ok(ApiResponse.success("登入成功", null));
		   }catch(CertException e) {
			   return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					  .body(ApiResponse.error(401, "登入失敗" + e.getMessage()));
		   }
	}
	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(HttpSession session){
		if(session.getAttribute("UserCert") == null ) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				   .body(ApiResponse.error(401, "登出失敗"));
		}
		session.invalidate();
		return ResponseEntity.ok(ApiResponse.success("登出成功", null));
	}
	
	@GetMapping("/check-login")
	public ResponseEntity<ApiResponse<UserCert>> checklogin(HttpSession session){
		UserCert cert = (UserCert) session.getAttribute("UserCert");
		if(cert != null) {
			return ResponseEntity.ok(ApiResponse.success("使用者已登入", cert));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiResponse.error(401, "尚未登入"));
	}
}
