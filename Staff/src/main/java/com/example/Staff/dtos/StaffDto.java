package com.example.Staff.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {

	private Long id;
	private String name;
	private String designation;
	private Date dateOfJoining;
	private String contactNumber;
}
