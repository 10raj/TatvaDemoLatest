package com.example.Readers.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadersDto {

	private Long id;
	private String email;
	private String name;
	private String contactNumber;
	private String address;
	private List<Book> books;
}
