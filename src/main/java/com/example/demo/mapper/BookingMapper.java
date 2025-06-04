package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.BookingDto;
import com.example.demo.model.entity.Booking;
import com.example.demo.model.entity.Status;

@Component
public class BookingMapper {
	@Autowired
	private ModelMapper modelmapper;
	
	public Booking toEntity(BookingDto bookingDto) {
		Booking booking = modelmapper.map(bookingDto, Booking.class);
		if(bookingDto.getStatus() != null) {
			booking.setStatus(Status.valueOf(bookingDto.getStatus()));
		}
		return booking;
	}
	
	public BookingDto toDto(Booking booking) {
		BookingDto bookingDto = modelmapper.map(booking, BookingDto.class);
		if(booking.getStatus() != null) {
			bookingDto.setStatus(booking.getStatus().name());
		}
		return bookingDto;
	}
}
