package com.example.demo.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDisplayDto {
	private Integer bookingId;
	private String location;
	private String note;
	private String serviceName;
	private String staffName;
	private String status;
	private LocalDate time;
}
