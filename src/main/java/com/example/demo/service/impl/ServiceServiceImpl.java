package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.exception.ServiceNotFoundException;
import com.example.demo.mapper.ServiceMapper;
import com.example.demo.model.dto.ServiceDto;
import com.example.demo.model.entity.Service;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceMapper serviceMapper;
	
	@Override
	public List<ServiceDto> getAllService() {
		List<Service> services = serviceRepository.findAll();
		List<ServiceDto> dtos = services.stream()
								.map(service -> serviceMapper.toDto(service))
								.toList();
		return dtos;
	}

	@Override
	public ServiceDto getServiceById(Integer serviceId) {
		Optional<Service> serviceOpt = serviceRepository.findById(serviceId);
		if(serviceOpt.isPresent()) {
			return serviceMapper.toDto(serviceOpt.get());
		}else {
			throw new ServiceNotFoundException("查無服務");
		}
	}

	@Override
	public void deleteService(Integer serviceId) {
		Optional<Service> serviceOpt = serviceRepository.findById(serviceId);
		if(serviceOpt.isPresent()) {
			serviceRepository.delete(serviceOpt.get());
		}else {
			throw new ServiceNotFoundException("查無服務");
		}
	}

	@Override
	public void createService(ServiceDto serviceDto) {
		Service service = serviceMapper.toEntity(serviceDto);
		serviceRepository.save(service);
	}

	@Override
	public void updateService(Integer serviceId, ServiceDto dto) {
		Optional<Service> serviceOpt = serviceRepository.findById(serviceId);
		if(serviceOpt.isPresent()) {
			Service service = serviceOpt.get();
			service.setServiceName(dto.getServiceName());
			service.setPrice(dto.getPrice());
			service.setDurationMinute(dto.getDurationMinute());
			service.setDescription(dto.getDescription());
			service.setActive(dto.getActive());
			serviceRepository.save(service);
		}else {
			throw new ServiceNotFoundException("查無服務");
		}
		
	}

}
