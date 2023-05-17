package com.example.Staff.services;

import java.util.List;

import com.example.Staff.dtos.StaffDto;
import com.example.Staff.entity.Staff;

public interface StaffService {

	void deleteById(Long id);

	StaffDto getStaffById(Long id);

	List<StaffDto> getAllStaff();

	StaffDto save(StaffDto staffDto);

}
