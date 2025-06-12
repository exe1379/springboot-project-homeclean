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

import jakarta.transaction.Transactional;
@Service
public class ServiceServiceImpl implements ServiceService {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceMapper serviceMapper;
	
	@Override
	public List<ServiceDto> getAllServices() {
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
	public ServiceDto createService(ServiceDto serviceDto) {
		serviceDto.setServiceId(null);
		ServiceEntity service = serviceMapper.toEntity(serviceDto);
		ServiceEntity saved = serviceRepository.save(service);
		return serviceMapper.toDto(saved);
	}
	@Transactional
	@Override
	public ServiceDto updateService(Integer serviceId, ServiceDto dto) {
		ServiceEntity service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("查無服務"));
		service.setServiceName(dto.getServiceName());
		service.setPrice(dto.getPrice());
		service.setDurationMinute(dto.getDurationMinute());
		service.setDescription(dto.getDescription());
		service.setActive(dto.getActive());
		serviceRepository.save(service);
		ServiceDto updated = serviceMapper.toDto(service);
		return updated;
	}
	
	public void toggleServiceActive(Integer serviceId, Boolean isActive) {
		ServiceEntity service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("找不到該服務"));
		service.setActive(isActive);
		serviceRepository.save(service);
	}
}
