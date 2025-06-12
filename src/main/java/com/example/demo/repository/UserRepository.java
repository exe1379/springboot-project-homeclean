package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from User u left join fetch u.reviews")
	List<User> findAllWithReviews();
	@Query("select u from User u left join fetch u.bookings")
	List<User> findAllWithBookings();
	
	User findByUserName(String username);
		
    User findByEmailToken(String emailToken);

    User findByEmail(String email);
}
