package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ServiceMapper;
import com.example.demo.model.dto.ServiceDto;
import com.example.demo.model.entity.ServiceEntity;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.service.ServiceService;
@Service
public class ServiceServiceImpl implements ServiceService {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceMapper serviceMapper;
	
	@Override
	public List<ServiceDto> getAllService() {
		List<ServiceEntity> services = serviceRepository.findAll();
		return services.stream()
				.map(serviceMapper::toDto)
				.toList();
	}

	@Override
	public ServiceDto getServiceById(Integer serviceId) {
		ServiceEntity service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("查無服務"));
		return serviceMapper.toDto(service);
	}

	@Override
	public void deleteService(Integer serviceId) {
		ServiceEntity service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("查無服務"));
		serviceRepository.delete(service);
	}

	@Override
	public void createService(ServiceDto serviceDto) {
		ServiceEntity service = serviceMapper.toEntity(serviceDto);
		serviceRepository.save(service);
	}

	@Override
	public void updateService(Integer serviceId, ServiceDto dto) {
		ServiceEntity service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("查無服務"));

		service.setServiceName(dto.getServiceName());
		service.setPrice(dto.getPrice());
		service.setDurationMinute(dto.getDurationMinute());
		service.setDescription(dto.getDescription());
		service.setActive(dto.getActive());

		serviceRepository.save(service);
	}
}
