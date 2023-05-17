
package com.example.Books.Publishers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Books.Publishers.dtos.BookRequestDto;
import com.example.Books.Publishers.dtos.BookResponseDto;
import com.example.Books.Publishers.entity.Book;
import com.example.Books.Publishers.services.BooksService;

@RestController
@RequestMapping("/book")
public class BooksController {

	
	@Autowired
	private BooksService booksService;
	
	
	@GetMapping
	public List<BookResponseDto> getAllBooks(){
		return booksService.getAll();
	}
	@GetMapping("/{id}")
	public BookResponseDto getBookById(@PathVariable("id") Long id){
		return booksService.getBookById(id);
	}
	@GetMapping("/reader/{id}")
	public List<BookResponseDto> getBookByReaderId(@PathVariable("id") Long readerId){
		return booksService.getBookByReaderId(readerId);
	}
	@GetMapping("/book_name/{bookName}")
	public ResponseEntity<BookResponseDto> getBookByDueDate(@PathVariable("bookName") String bookName){
		return ResponseEntity.ok(booksService.getBookByName(bookName));
	}
	@GetMapping("/readerdueList")
	public long[] getBookByDueDate(){
		return booksService.bookByDueDate();
	}
	@PostMapping
	public BookResponseDto saveBook(@RequestBody BookRequestDto bookDto){
		return booksService.save(bookDto);
	}
	@PutMapping
	public BookResponseDto updateBook(@RequestBody BookRequestDto bookDto){
		return booksService.save(bookDto);
	}
	@DeleteMapping("/{id}")
	public void deleteBookById(@PathVariable("id") Long id){
		booksService.deleteBookById(id);
	}
}
