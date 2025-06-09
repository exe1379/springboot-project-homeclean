package com.example.demo.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {
	private Integer staffId;
	private Integer serviceId;
	private String location;
	private String note;
	private LocalDateTime time;
}
