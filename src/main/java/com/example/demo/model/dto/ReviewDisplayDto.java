package com.example.demo.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDisplayDto {
	private String serviceName;
	private String userName;
	private LocalDateTime createdAt;
	private String staffName;
	private Integer rating;
	private String comment;
}
