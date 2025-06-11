package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ServiceDto;
import com.example.demo.model.entity.Service;

@Component
public class ServiceMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public Service toEntity(ServiceDto serviceDto) {
		return modelMapper.map(serviceDto, Service.class);
	}
	
	public ServiceDto toDto(Service service) {
		return modelMapper.map(service, ServiceDto.class);
	}
}
