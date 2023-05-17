package com.example.AppGateWay.config;

import java.time.Duration;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.JwtVerificationException;
import com.okta.jwt.JwtVerifiers;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GatWayAuthenticationFilter extends AbstractGatewayFilterFactory<GatWayAuthenticationFilter.Config> {
	public static class Config {
	}

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${AuthorizationUri}")
	private String authUri;
	
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			String token = exchange.getRequest().getHeaders().getFirst("Authorization");
			ResponseEntity<String> response= restTemplate.getForEntity(authUri, String.class, token);
			if(response.getStatusCode()!=HttpStatusCode.valueOf(200))
				throw new SecurityException("Given Authorization token is not valid");
			return chain.filter(exchange);
		});
	}
}

