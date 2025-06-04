package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from user u left join fetch u.Review")
	List<User> findAllWithReviews();
	@Query("select u from user u left join fetch u.booking")
	List<User> findAllWithBookings();
	@Query(value = "select id, username, name, email, phone, hash_password, hash_salt, role, created_at from user where username =:username", nativeQuery = true)
	User getUser(String username);
}
