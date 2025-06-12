package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ServiceDto;

public interface ServiceService {
	
	public List<ServiceDto> getAllServices();
	
	public ServiceDto getServiceById(Integer serviceId);
	
	public void deleteService(Integer ServiceId);
	
	public ServiceDto createService(ServiceDto serviceDto);
	
	public ServiceDto updateService(Integer serviceId, ServiceDto serviceDto);
}
