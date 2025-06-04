package com.example.demo.model.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
	private Integer serviceId;
	private String description;
	private Integer durationMinute;
	private Integer price;
	private String serviceName;
	private Boolean active;
}
