package com.example.demo.model.entity;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer userId;
	@Column(nullable = false)
	private String passwordHash;
	@Column(nullable = false)
	private String salt;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = true)
	private String name;
	@Column(nullable = false)
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;
	@Column(name = "phone")
	private String phoneNumber;
	@Column(name = "created_at")
	private LocalDateTime createdDate;
	@Column(nullable = false)
	private boolean emailVerified;
	@Column(name = "email_token")
	private String emailToken;
	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
}
