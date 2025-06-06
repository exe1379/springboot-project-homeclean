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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer staffId;
	
	@Column(name = "certifications")
	private String certifications;
	
	@Column(name = "experience")
	private String experience;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	@Column(name = "rating")
	private Double rating;
}
