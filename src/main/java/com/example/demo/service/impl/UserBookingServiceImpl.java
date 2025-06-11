package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AccessDeniedException;
import com.example.demo.model.dto.BookingDisplayDto;
import com.example.demo.model.dto.BookingRequestDto;
import com.example.demo.model.entity.Booking;
import com.example.demo.model.entity.Staff;
import com.example.demo.model.entity.Status;
import com.example.demo.model.entity.User;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.StaffRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserBookingService;
@Service
public class UserBookingServiceImpl implements UserBookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Override
	public List<BookingDisplayDto> getAllBookings(String username) {
		List<Booking> bookings = bookingRepository.findAllByUser_UserName(username);
		List<BookingDisplayDto> dtos = bookings.stream()
		.map(this::convertToDisplayDto)
		.toList();
		return dtos;
	}

	@Override
	public BookingDisplayDto getBookingById(String username, Integer bookingId) {
		Optional<Booking> bookingOpt = bookingRepository.findByIdAndUser_UserName(bookingId, username);
		if(bookingOpt.isPresent()) {
			return convertToDisplayDto(bookingOpt.get());
		}else{
			throw new AccessDeniedException("找不到該筆預約或無權限");
		}
	}

	@Override
	public void createBooking(BookingRequestDto bookingRequestDto, String username) {
		com.example.demo.model.entity.Service service = serviceRepository
		        .findById(bookingRequestDto.getServiceId())
		        .orElseThrow(() -> new RuntimeException("找不到服務"));
		Booking booking = convertToBookingEntity(bookingRequestDto, username);
		booking.setService(service);
		bookingRepository.save(booking);
	}

	@Override
	public void deleteBooking(Integer bookingId, String username) {
		Booking booking = bookingRepository.findByIdAndUser_UserName(bookingId, username)
		.orElseThrow(() -> new AccessDeniedException("找不到該筆預約或無權限"));
		bookingRepository.delete(booking);
	}

	@Override
	public BookingDisplayDto updateBooking(BookingRequestDto dto, Integer bookingId, String username) {
		Booking booking = bookingRepository.findByIdAndUser_UserName(bookingId, username)
		.orElseThrow(() -> new AccessDeniedException("找不到該筆預約或無權限"));
		booking.setLocation(dto.getLocation());
		booking.setNote(dto.getNote());
		booking.setTime(dto.getTime());
		bookingRepository.save(booking);
		return convertToDisplayDto(booking);
	}
	
	private BookingDisplayDto convertToDisplayDto(Booking booking) {
		return new BookingDisplayDto(
		booking.getBookingId(),
		booking.getLocation(),
		booking.getNote(),
		booking.getService() != null ? booking.getService().getServiceName() : null,
	    booking.getStaff() != null ? booking.getStaff().getName() : null,
		booking.getStatus().name(),
		booking.getTime()
		);
	}
	
	private Booking convertToBookingEntity(BookingRequestDto dto, String username) {
	    User user = userRepository.findByUserName(username);
	    
	    com.example.demo.model.entity.Service  service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("找不到服務"));
	
	    Staff staff = null;
	    if (dto.getStaffId() != null) {
	        staff = staffRepository.findById(dto.getStaffId())
	                 .orElseThrow(() -> new RuntimeException("找不到員工"));
	    }
	    Booking booking = new Booking();
	    booking.setUser(user);
	    booking.setService(service);
	    booking.setServiceId(dto.getServiceId());
	    booking.setLocation(dto.getLocation());
	    booking.setNote(dto.getNote());
	    booking.setTime(dto.getTime());
	    booking.setStatus(Status.RESERVED); // 預設狀態
	    if (staff != null) {
	        booking.setStaffId(dto.getStaffId());
	        booking.setStaff(staff);
	    }

	    return booking;
	}
}
