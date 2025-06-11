package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ServiceDto;

public interface ServiceService {
	
	public List<ServiceDto> getAllService();
	
	public ServiceDto getServiceById(Integer serviceId);
	
	public void deleteService(Integer ServiceId);
	
	public void createService(ServiceDto serviceDto);
	
	public void updateService(Integer serviceId, ServiceDto serviceDto);
}
