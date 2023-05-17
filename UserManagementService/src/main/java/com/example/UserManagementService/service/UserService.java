package com.example.UserManagementService.service;

import java.util.List;

import com.example.UserManagementService.dto.UserAuthenticationDto;
import com.example.UserManagementService.dto.UserRequestDto;
import com.example.UserManagementService.dto.UserValidationDto;
import com.example.UserManagementService.entity.Role;
import com.example.UserManagementService.entity.User;


public interface UserService {

	User save(User user);
	void setRoles(Long id, List<Role> roles);
	List<User> getUsers();
	User getByUserEmail(String email);
	String userRegistration(UserRequestDto userRequestDto);
	void userAuthentication(UserAuthenticationDto userAuthenticationDto) throws Exception;
	User getUser(String userName);
	void userValidation(UserValidationDto userValidationDto) throws Exception;
	
	
}
