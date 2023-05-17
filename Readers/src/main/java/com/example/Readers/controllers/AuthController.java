//package com.example.Readers.controllers;
//
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.Readers.config.JwtUtil;
//import com.example.Readers.dtos.LogInRequest;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/readers/lm/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//	private final AuthenticationManager authenticationManager; 
//	private final JwtUtil jwtUtil;
//	private final UserDetailsService userDetailsService;
//	
//	@PostMapping
//	public ResponseEntity<String> Authentication(@RequestBody LogInRequest logInRequest){
//		authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(logInRequest.getUserName(), logInRequest.getPassword()));
//		try {
//			final UserDetails userDetails = userDetailsService.loadUserByUsername(logInRequest.getUserName());
//			return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
//		}
//		catch (Exception e) {
//			return ResponseEntity.status(400).body("unauthenticated user ::" + logInRequest.getUserName());
//		}
//	}
//}
