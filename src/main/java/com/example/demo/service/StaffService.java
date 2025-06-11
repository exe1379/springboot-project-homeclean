package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.StaffDto;

public interface StaffService {
	
	public List<StaffDto> findAllStaffs();
	
	public StaffDto findStaffById(Integer staffId);
	
	public void createStaff(StaffDto staffDto);
	
	public void deleteStaff(Integer staffId);
	
	public void updateStaff(Integer staffId, StaffDto staffDto);
}
