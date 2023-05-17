package com.example.UserManagementService.dto;

import java.util.List;

import com.example.UserManagementService.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserValidationDto {

	private String username;
	private List<Role>  roles;
}
