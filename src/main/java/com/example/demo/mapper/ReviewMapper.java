package com.example.demo.mapper;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ReviewDto;
import com.example.demo.model.entity.Review;

@Component
public class ReviewMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public Review ToEntity(ReviewDto reviewDto, Integer userId) {
		Review review = modelMapper.map(reviewDto, Review.class);
		review.setUserId(userId);
		review.setCreatedAt(LocalDateTime.now());
		return review;
	}
	
	public ReviewDto toDto(Review review) {
		return modelMapper.map(review, ReviewDto.class);
	}
}
