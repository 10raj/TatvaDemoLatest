package com.example.Readers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private Long id;
	private String Title;
	private String Edition;
	private String category;
	private Double price;
	private Long isbn;
	private String authorNames;
	private Publisher publisher;
}
