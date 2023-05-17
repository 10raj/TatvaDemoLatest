package com.example.Staff.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Staff.dtos.Role;
import com.example.Staff.dtos.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final RestTemplate restTemplate;
	
	@Value("${UserObject}")
	String usercall;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		log.info("loadUserByUsername method called");
		ResponseEntity<User> userResponse = restTemplate.getForEntity(usercall, User.class,userName);
		if(userResponse.getStatusCode()!=HttpStatus.OK)
			throw new UsernameNotFoundException("Given userName :"+userName+"doesn't exist");
		
		List<SimpleGrantedAuthority> userAuthorities= new ArrayList<>();
		List<Role> roles =userResponse.getBody().getRoles();
		roles.forEach((role)->userAuthorities.add(new SimpleGrantedAuthority(role.getRoleName())));
		log.info(roles.toString(),userResponse.getBody());
		log.info(userResponse.getBody().toString());
		return UserPrinciple.build(userResponse.getBody(), userAuthorities);
	}

}
