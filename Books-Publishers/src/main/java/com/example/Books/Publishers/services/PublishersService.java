package com.example.Books.Publishers.services;

import java.util.List;

import com.example.Books.Publishers.dtos.PublisherDto;
import com.example.Books.Publishers.dtos.PublisherResponseDto;
import com.example.Books.Publishers.entity.Publisher;

public interface PublishersService {

	List<PublisherDto> getAll();

	PublisherDto getBookById(Long id);

	PublisherDto save(PublisherDto publisherDto);

	void deleteBookById(Long id);

}
