package com.example.Staff.config.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestClientConfig {
	
	@Bean
	public RestTemplate createRestTamplate() {
		return new RestTemplate();
	}
	
    
}
