package com.example.demo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer bookingId;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "service_id")
	private Integer serviceId;
	
	@Column(name = "staff_id")
	private Integer staffId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status" , nullable = false)
	private Status status;
	
	@Column(name = "time")
	private LocalDateTime time;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "service_id", insertable = false, updatable = false)
	private ServiceEntity service;
	
	@ManyToOne
	@JoinColumn(name = "staff_id", insertable = false , updatable = false)
	private Staff staff;
}
