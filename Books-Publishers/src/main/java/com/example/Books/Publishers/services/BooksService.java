package com.example.Books.Publishers.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.Books.Publishers.dtos.BookRequestDto;
import com.example.Books.Publishers.dtos.BookResponseDto;
import com.example.Books.Publishers.entity.Book;

public interface BooksService {

	List<BookResponseDto> getAll();
	BookResponseDto getBookById(Long id);
	BookResponseDto save(BookRequestDto bookDto);
	void deleteBookById(Long id);
	List<BookResponseDto> getBookByReaderId(Long readerId);
	 long[] bookByDueDate();
	BookResponseDto getBookByName(String bookName);
	
}
