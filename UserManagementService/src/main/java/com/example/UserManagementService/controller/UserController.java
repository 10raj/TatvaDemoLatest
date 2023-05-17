package com.example.UserManagementService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserManagementService.dto.UserAuthenticationDto;
import com.example.UserManagementService.dto.UserRequestDto;
import com.example.UserManagementService.dto.UserValidationDto;
import com.example.UserManagementService.entity.Role;
import com.example.UserManagementService.entity.User;
import com.example.UserManagementService.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}
	@GetMapping("/{userName}")
	public User getUserByUserName(@PathVariable("userName") String userName) {
		return userService.getUser(userName);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> UserRegistration(@RequestBody UserRequestDto userRequestDto) {
		log.info("registration method of UserController called");
		return ResponseEntity.ok(userService.userRegistration(userRequestDto));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> UserAuthentication(@RequestBody UserAuthenticationDto userAuthenticationDto) {
		log.info("authenticate Controller called");
		try {
			userService.userAuthentication(userAuthenticationDto);
			return new  ResponseEntity<>("User Authentication Successfull", HttpStatus.OK);
		}
		catch(Exception e) {
			return new  ResponseEntity<>("User Authentication Falied", HttpStatus.BAD_REQUEST);
	}
	}
	@PostMapping("/validate")
	public ResponseEntity<String> UserValidation(@RequestBody UserValidationDto userValidationDto) {
		log.info("validate Controller called");
		try {
			userService.userValidation(userValidationDto);
			return new  ResponseEntity<>("User Validation Successfull", HttpStatus.OK);
		}
		catch(Exception e) {
			return new  ResponseEntity<>("User Validation Falied", HttpStatus.BAD_REQUEST);
	}
	}
	
}
