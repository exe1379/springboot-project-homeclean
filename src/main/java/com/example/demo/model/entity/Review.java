package com.example.demo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer reviewId;
	@Column(name = "booking_id")
	private Integer bookingId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "staff_id")
	private Integer staffId;
	@Column(name = "service_Id")
	private Integer serviceId;
	@Min(1)
	@Max(5)
	@Column(name = "rating")
	private Integer rating;
	@Column(name = "comment")
	private String comment;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
}
