package com.example.Books.Publishers.dtos;

import java.util.List;


import com.example.Books.Publishers.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponseDto {

	private Long id;
	private String name;
	private String address;
	private List<BookResponseDto> bookRequestDtos;
}
