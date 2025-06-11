package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<Void>> handleAccessDenied(AccessDeniedException e){
		return ResponseEntity.status(403).body(ApiResponse.error(403,"權限不足" + e.getMessage()));
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException e){
		return ResponseEntity.badRequest().body(ApiResponse.error(400, "參數驗證錯誤"));
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<Void>> handleRuntime(RuntimeException e){
		return ResponseEntity.badRequest().body(ApiResponse.error(400, "操作失敗：" + e.getMessage()));
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception e){
		return ResponseEntity.badRequest().body(ApiResponse.error(400, "伺服器錯誤，請稍後再試" + e.getMessage()));
	}
}
