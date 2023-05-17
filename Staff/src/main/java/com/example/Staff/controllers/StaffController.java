package com.example.Staff.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Staff.dtos.StaffDto;
import com.example.Staff.entity.Staff;
import com.example.Staff.services.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	StaffService staffService;
	
	@PostMapping
	public StaffDto staffRegistration(@RequestBody StaffDto staffDto) {
		return staffService.save(staffDto);
	}
	@PutMapping
	public StaffDto updateStaff(@RequestBody StaffDto staffDto) {
		return staffService.save(staffDto);
	}
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_admin')")
	public List<StaffDto> getAllStaff() {
		return staffService.getAllStaff();
	}
	@GetMapping("/{Id}")
	public StaffDto getStaffById(@PathVariable("id") Long id) {
		return staffService.getStaffById(id);
	}
	@DeleteMapping("/{Id}")
	public void staffRegistration(@PathVariable("id") Long id) {
		staffService.deleteById(id);
	}
	
	
}
