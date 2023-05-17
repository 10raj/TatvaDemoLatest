package com.example.Readers.controllers;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Readers.dtos.ReadersDto;
import com.example.Readers.entity.Readers;
import com.example.Readers.services.ReadersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/readers")
public class ReadersController {

	@Autowired
	private ReadersService readersService;
	
	@GetMapping
	public List<ReadersDto> getAllReaders(HttpServletRequest httpServletRequest){
		log.info(httpServletRequest.getHeader("Authorization"));
		log.info(httpServletRequest.getUserPrincipal().toString());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 log.info(auth.getPrincipal().toString(),auth.getAuthorities().toString());
		  new InMemoryUserDetailsManager(
				 User.withUsername(httpServletRequest.getUserPrincipal().getName()).password("password").roles("admin").build());
		  log.info(auth.getPrincipal().toString(),auth.getAuthorities().toString());
		return readersService.getAll();
	}
	
	@GetMapping("/{id}")
	public ReadersDto getReaderById(@PathVariable("id") Long id, HttpServletRequest httpRequest){
		return readersService.getReaderById(id,httpRequest);
	}
	
	@PostMapping
	public ReadersDto RegisterReader(@RequestBody ReadersDto readersDto){
		return readersService.save(readersDto);
	}
	@PutMapping
	public ReadersDto UpdateReader(@RequestBody ReadersDto readersDto){
		return readersService.save(readersDto);
	}
	@DeleteMapping("/{id}")
	public void RegisterReader(@PathVariable("id") Long id){
		readersService.delete(id);
	}
	
	
	
}
