package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.BookingDisplayDto;
import com.example.demo.model.dto.BookingRequestDto;

public interface UserBookingService {
	
	public List<BookingDisplayDto> getAllBookings(String username);
	
	public BookingDisplayDto getBookingById(String username,Integer bookingId);
	
	public void createBooking(BookingRequestDto bookingRequestDto, String username);
	
	public void deleteBooking(Integer bookingId, String username);
	
	public BookingDisplayDto updateBooking(BookingRequestDto bookingRequestDto, Integer bookingId, String username);
}
