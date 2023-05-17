package com.example.AppGateWay.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

	@Autowired
	private AuthManager jwtAuthManager;
	
	@Autowired
	private AuthConverter authConverter;
	
@Bean
public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
	log.info("filter CHain called");
	AuthenticationWebFilter jwtFilter = new AuthenticationWebFilter(jwtAuthManager);
	jwtFilter.setServerAuthenticationConverter(authConverter);
    return http
    		.csrf().disable()
    		.formLogin().disable()
    		.httpBasic().disable()
            .authorizeExchange()
            .pathMatchers("/api/v1/user/**").permitAll()
            .pathMatchers("/auth/**").permitAll()
            .pathMatchers("/readers/**").hasRole("ADMIN")
            .pathMatchers("/book/book_name/**").permitAll()
            .and().cors()
            .and()
            //.authorizeExchange().anyExchange().authenticated().and()
            .addFilterAt(jwtFilter,SecurityWebFiltersOrder.AUTHENTICATION)
            
            .build();
	}

//@Bean
//public CorsWebFilter corsWebFilter() {
//    CorsConfiguration corsConfig = new CorsConfiguration();
//    corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//    corsConfig.setMaxAge(3600L);
//    corsConfig.addAllowedMethod("*");
//    corsConfig.addAllowedHeader("Requestor-Type");
//    corsConfig.addExposedHeader("X-Get-Header");
//
//    UrlBasedCorsConfigurationSource source =
//        new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", corsConfig);
//
//    return new CorsWebFilter(source);
//}
}