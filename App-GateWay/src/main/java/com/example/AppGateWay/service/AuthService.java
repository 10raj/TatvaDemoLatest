package com.example.AppGateWay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.AppGateWay.dtos.ReqResp;
import com.example.AppGateWay.security.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	@Value("${UserObject}")
	String usercall;

	final JWTService jwtService;
	final RestTemplate restTemplate;

	public ResponseEntity<ReqResp<String>> login(String userName, String password) {
		// Mono<UserDetails> user =
		// userService.findByUsername(userID.toLowerCase()).switchIfEmpty(Mono.error(new
		// ResponseExceptionModel("User not registered", 404)));

		ResponseEntity<User> userResponse = restTemplate.getForEntity(usercall, User.class, userName);
		if (userResponse.getStatusCode() != HttpStatus.OK)
			throw new UsernameNotFoundException("Given userName :" + userName + "doesn't exist");

		if (userResponse.getBody().getUserName().equals(userName)
				&& password.equals(userResponse.getBody().getPassword()))
			return ResponseEntity
					.ok(new ReqResp<>(jwtService.generate(userResponse.getBody().getUserName()), "Success"));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ReqResp<>("Wrong Credentials", "Wrong Credentials"));

	}
}
