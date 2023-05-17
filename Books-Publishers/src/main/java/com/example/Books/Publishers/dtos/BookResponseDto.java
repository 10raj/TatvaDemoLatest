package com.example.Books.Publishers.dtos;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.Books.Publishers.entity.Publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookResponseDto {
	
	
	private Long id;
	private String Title;
	private String Edition;
	private String category;
	private Double price;
	private Long isbn;
	private String authorNames;
	private Publisher publisher;
	private Long readerId;
	private Date isuueDate;
	private Date returnDate;
}
