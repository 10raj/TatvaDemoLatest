package com.example.Books.Publishers.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Books.Publishers.entity.User;
import com.example.Books.Publishers.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lm/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}
}
