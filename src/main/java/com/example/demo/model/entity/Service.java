package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service")
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer serviceId;
	@Column(name = "description")
	private String description;
	@Column(name = "duration")
	private Integer durationMinute;
	@Column(name = "active", nullable = false)
	private Boolean active;
	@Column(name = "price")
	private Integer price;
	@Column(name = "service_name", nullable = false)
	private String serviceName;
}
