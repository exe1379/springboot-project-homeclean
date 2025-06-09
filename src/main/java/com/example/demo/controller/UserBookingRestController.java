package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.BookingDisplayDto;
import com.example.demo.model.dto.BookingRequestDto;
import com.example.demo.model.dto.UserCert;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserBookingService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/booking")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserBookingRestController {
	
	@Autowired
	private UserBookingService userBookingService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<BookingDisplayDto>>> getAllBookings(HttpSession session){
		UserCert userCert = (UserCert) session.getAttribute("UserCert");
		List<BookingDisplayDto> dtos = userBookingService.getAllBookings(userCert.getUserName());
		return ResponseEntity.ok(ApiResponse.success("查詢成功", dtos));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<BookingDisplayDto>> getBookingById(@PathVariable Integer id , HttpSession session){
		UserCert cert = (UserCert) session.getAttribute("UserCert");
		BookingDisplayDto displayDto = userBookingService.getBookingById(cert.getUserName(), id);
		return ResponseEntity.ok(ApiResponse.success("查詢成功", displayDto));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> createBooking(@RequestBody BookingRequestDto dto , HttpSession session){
		UserCert cert = (UserCert) session.getAttribute("UserCert");
		userBookingService.createBooking(dto, cert.getUserName());
		return ResponseEntity.ok(ApiResponse.success("新增預約成功", null));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<BookingDisplayDto>> updateBooking(@RequestBody BookingRequestDto dto, @PathVariable Integer bookingId, HttpSession session){
		UserCert cert = (UserCert) session.getAttribute("UserCert");
		BookingDisplayDto updatedDto = userBookingService.updateBooking(dto, bookingId, cert.getUserName());
		return ResponseEntity.ok(ApiResponse.success("更新成功", updatedDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteBooking(@PathVariable Integer id, HttpSession session){
		UserCert cert = (UserCert) session.getAttribute("UserCert");
		userBookingService.deleteBooking(id, cert.getUserName());
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}
}
