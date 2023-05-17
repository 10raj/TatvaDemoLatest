package com.example.Books.Publishers.services;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Books.Publishers.entity.Role;
import com.example.Books.Publishers.entity.User;
import com.example.Books.Publishers.repository.UserRepository;
import com.example.Books.Publishers.securityConfig.UserPrinciple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		log.info("loadUserByUsername method called");
		
		User user = userRepository.findByUserName(userName)
				.orElseThrow(()-> new IllegalArgumentException("Invalid User Name : "+userName));
		List<SimpleGrantedAuthority> userAuthorities= new ArrayList<>();
		List<Role> roles =user.getRoles();
		roles.forEach((role)->userAuthorities.add(new SimpleGrantedAuthority(role.getRoleName())));
		return UserPrinciple.build(user, userAuthorities);
	}

}
