package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
}
