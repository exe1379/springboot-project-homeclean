package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.StaffDto;
import com.example.demo.model.entity.Staff;

@Component
public class StaffMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public Staff ToEntity(StaffDto staffDto) {
		return modelMapper.map(staffDto, Staff.class);
	}
	
	public StaffDto toDto(Staff staff) {
		return modelMapper.map(staff, StaffDto.class);
	}
}
