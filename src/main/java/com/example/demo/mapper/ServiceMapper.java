package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ServiceDto;
import com.example.demo.model.entity.ServiceEntity;

@Component
public class ServiceMapper {
	@Autowired
	private ModelMapper modelMapper;

	public ServiceEntity toEntity(ServiceDto serviceDto) {
		return modelMapper.map(serviceDto, ServiceEntity.class);
	}

	public ServiceDto toDto(ServiceEntity service) {
		return modelMapper.map(service, ServiceDto.class);
	}
}
