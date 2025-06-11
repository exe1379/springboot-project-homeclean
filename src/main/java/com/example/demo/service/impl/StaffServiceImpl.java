package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.model.dto.StaffDto;
import com.example.demo.model.entity.Staff;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.StaffService;
@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public List<StaffDto> findAllStaffs() {
		List<Staff> staffs = staffRepository.findAll();
		List<StaffDto> dtos = staffs.stream()
				.map(staff -> staffMapper.toDto(staff))
				.toList();
		return dtos;
	}

	@Override
	public StaffDto findStaffById(Integer staffId) {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("查無此人"));
		return staffMapper.toDto(staff);
	}

	@Override
	public void createStaff(StaffDto staffDto) {
		staffDto.setStaffId(null);
		Staff staff = staffMapper.ToEntity(staffDto);
		staffRepository.save(staff);
	}

	@Override
	public void deleteStaff(Integer staffId) {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("查無此人"));
		staffRepository.delete(staff);
	}

	@Override
	public void updateStaff(Integer staffId, StaffDto staffDto) {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("查無此人"));
		staff.setName(staffDto.getName());
		staff.setPhotoUrl(staffDto.getPhotoUrl());
		staff.setIsActive(staffDto.getIsActive());
		staff.setExperience(staffDto.getExperience());
		staff.setCertifications(staffDto.getCertifications());
	}
}
