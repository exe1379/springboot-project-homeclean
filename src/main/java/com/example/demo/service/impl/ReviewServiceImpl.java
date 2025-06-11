package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AccessDeniedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ReviewMapper;
import com.example.demo.model.dto.ReviewDto;
import com.example.demo.model.entity.Booking;
import com.example.demo.model.entity.Review;
import com.example.demo.model.entity.Staff;
import com.example.demo.model.entity.Status;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public List<ReviewDto> getAllReviews() {
		List<Review> reviews = reviewRepository.findAll();
		List<ReviewDto> dtos = reviews.stream()
							.map(review -> reviewMapper.toDto(review))
							.toList();
		return dtos;
	}

	@Override
	public ReviewDto getReviewById(Integer reviewId) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ResourceNotFoundException("查無評論"));
		ReviewDto dto = reviewMapper.toDto(review);
		return dto;
	}
	
	@Override
	public void createReview(ReviewDto reviewDto, Integer currentUserId) {
		Booking booking = bookingRepository.findById(currentUserId)
				.orElseThrow(() ->new ResourceNotFoundException("查無預約"));
		if(!booking.getUser().getUserId().equals(currentUserId)) {
			throw new AccessDeniedException("只能對自己的預約評論");
		}
		if(!booking.getStatus().equals(Status.COMPLETED)) {
			throw new AccessDeniedException("只能對已完成的預約評論");
		}
		if(reviewRepository.existsByBookingId(reviewDto.getBookingId())){
			throw new AccessDeniedException("已對此預約進行評論");
		}
		Review review = reviewMapper.ToEntity(reviewDto, currentUserId);
		review.setCreatedAt(LocalDateTime.now());
		reviewRepository.save(review);
		
		Double avgRating = reviewRepository.calculateAverageRatingByStaffId(reviewDto.getStaffId());
		Staff staff = staffRepository.findById(reviewDto.getStaffId())
		.orElseThrow(() -> new ResourceNotFoundException("查無員工"));
		staff.setRating(avgRating);
		staffRepository.save(staff);
	}


	@Override
	public void deleteReview(Integer reviewId, Integer userId) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ResourceNotFoundException("查無評論"));
		reviewRepository.delete(review);
	}

	@Override
	public void updateReview(Integer reviewId, ReviewDto reviewDto) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ResourceNotFoundException("查無評論"));
		review.setComment(reviewDto.getComment());
		review.setRating(reviewDto.getRating());
		reviewRepository.save(review);
	}

	@Override
	public List<ReviewDto> getReviewsByUser(Integer userId) {
		List<Review> reviews = reviewRepository.findByUserId(userId);
		List<ReviewDto> dtos = reviews.stream().map(reviewMapper::toDto)
		.toList();
		return dtos;
	}
}
