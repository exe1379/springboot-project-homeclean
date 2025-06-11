package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	boolean existsByBookingId(Integer bookingId);

	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.staffId = :staffId")
	Double calculateAverageRatingByStaffId(@Param("staffId") Integer staffId);
	
	@Query("SELECT r FROM Review r WHERE r.userId = :userId")
	List<Review> findByUserId(@Param("userId") Integer userId);

}
