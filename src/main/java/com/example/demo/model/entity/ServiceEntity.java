package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service")
public class ServiceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
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
