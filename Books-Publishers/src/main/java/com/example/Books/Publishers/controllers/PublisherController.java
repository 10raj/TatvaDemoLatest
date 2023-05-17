package com.example.Books.Publishers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Books.Publishers.dtos.PublisherDto;
import com.example.Books.Publishers.dtos.PublisherResponseDto;
import com.example.Books.Publishers.entity.Book;
import com.example.Books.Publishers.entity.Publisher;
import com.example.Books.Publishers.services.PublishersService;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

	@Autowired
	private PublishersService publishersService;
	
	@GetMapping
	public List<PublisherDto> getAllPublishers(){
		return publishersService.getAll();
	}
	@GetMapping("/{id}")
	public PublisherDto getBookById(@PathVariable("id") Long id){
		return publishersService.getBookById(id);
	}
	@PostMapping
	public PublisherDto savePublisher(@RequestBody PublisherDto publishersRequestDto){
		return publishersService.save(publishersRequestDto);
	}
	@PutMapping
	public PublisherDto updatePublisher(@RequestBody PublisherDto publishersRequestDto){
		return publishersService.save(publishersRequestDto);
	}
	@DeleteMapping("/{id}")
	public void deleteBookById(@PathVariable("id") Long id){
		publishersService.deleteBookById(id);
	}
	
}
