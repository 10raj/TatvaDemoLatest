package com.example.Staff.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	private Long id;
	private String roleName;
	private Integer roleCode;
}
