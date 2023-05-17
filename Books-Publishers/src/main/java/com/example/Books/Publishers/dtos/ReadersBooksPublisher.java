package com.example.Books.Publishers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadersBooksPublisher {

	private Long id;
	private String name;
	private String address;
}
