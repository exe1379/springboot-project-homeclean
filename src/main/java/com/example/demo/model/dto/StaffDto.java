package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
	private Integer staffId;
	private String certifications;
	private String experience;
	private String name;
	private String photoUrl;
	private Boolean isActive;
	private Double rating;
}
