package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ReviewDto;

public interface ReviewService {
	
	public List<ReviewDto> getAllReviews();
	
	public List<ReviewDto> getReviewsByUser(Integer userId);
	
	public List<ReviewDto> getReviewsByStaffId(Integer staffId);
	
	public ReviewDto getReviewById(Integer reviewId);
	
	public void createReview(ReviewDto reviewDto, Integer userId);
	
	public void deleteReview(Integer reviewId, Integer userId);
	
	public void updateReview(Integer reviewId, ReviewDto reviewDto, Integer userId);
}
