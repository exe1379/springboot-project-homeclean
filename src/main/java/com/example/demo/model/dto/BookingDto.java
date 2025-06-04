package com.example.demo.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
	private Integer bookingId;
	private String location;
	private String note;
	private Integer serviceId;
	private Integer staffId;
	private String status;
	private LocalDate time;
}
