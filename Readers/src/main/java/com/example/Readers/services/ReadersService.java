package com.example.Readers.services;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.Readers.dtos.ReadersDto;
import com.example.Readers.entity.Readers;

public interface ReadersService {

	void delete(Long id);

	ReadersDto save(ReadersDto reader);

	ReadersDto getReaderById(Long id, HttpServletRequest httpRequest);

	List<ReadersDto> getAll();

}
