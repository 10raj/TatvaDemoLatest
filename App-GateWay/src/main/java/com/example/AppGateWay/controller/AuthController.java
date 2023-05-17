package com.example.AppGateWay.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.AppGateWay.dtos.ReqResp;
import com.example.AppGateWay.dtos.UserModel;
import com.example.AppGateWay.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;

	@PostMapping("/login")
    public ResponseEntity<ReqResp<String>> login(@RequestBody UserModel userLogin) {
        log.info("Logging endpoint {}", userLogin.toString());
        return authService.login(userLogin.getUserName(), userLogin.getPassword());
    }
}