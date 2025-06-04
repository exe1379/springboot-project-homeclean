package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
	private Integer bookingId;
	private Integer staffId;	
	private String comment;
	private Integer rating;
	private Integer serviceId;
}
