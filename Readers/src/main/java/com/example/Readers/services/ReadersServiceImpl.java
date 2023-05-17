package com.example.Readers.services;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Readers.config.JWTService;
import com.example.Readers.config.kafka.KafkaPublisher;
import com.example.Readers.dtos.Book;
import com.example.Readers.dtos.ReadersDto;
import com.example.Readers.entity.Readers;
import com.example.Readers.repository.ReadersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReadersServiceImpl implements ReadersService{
	private final RestTemplate restTemplate;
	private final ReadersRepository readersRepository;
	private final KafkaService kafkaService;
	private final JWTService jwtService;
	
	@Value("${LM-BOOKS-PUBLICASHERS.GETALL}")
	private String bookApiUrl; 

	@Override
	public void delete(Long id) {
		String kafkaMessage="";
		Readers readers = readersRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Given Reader id : "+id+"doesn't exist"));
		kafkaMessage="Given Reader with id"+id+"ahs been delted succesfully";
		kafkaService.sendMessageToKafka(kafkaMessage);
		readersRepository.delete(readers);
	}

	@Override
	public ReadersDto save(ReadersDto readersDto) {
		String kafkaMessage="";
		Readers readers= new Readers();
		if(readersDto.getId()==null || readersDto.getId()==0) {
			BeanUtils.copyProperties(readersDto, readers);
			kafkaMessage="Reader Registration completed";
		}
		else {
			Readers readersDb = readersRepository.findById(readersDto.getId())
					.orElseThrow(()-> new IllegalArgumentException("Given Reader id : "+readersDto.getId()+"doesn't exist"));
			BeanUtils.copyProperties(readersDto, readers);
			kafkaMessage="Reader Updated successfully";
		}
		kafkaService.sendMessageToKafka(kafkaMessage);
		return convertToDto(readersRepository.save(readers));
		 
	}
	
	private ReadersDto convertToDto(Readers readers) {
		ReadersDto readersDto = new ReadersDto();
		BeanUtils.copyProperties(readers, readersDto);
		return readersDto;
	}

	@Override
	public ReadersDto getReaderById(Long id, HttpServletRequest httpRequest) {
		String url= bookApiUrl;
		Readers readers = readersRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Given Reader id : "+id+"doesn't exist"));
		ReadersDto readersDto=convertToDto(readersRepository.save(readers));
		String token = httpRequest.getHeader("Authorization");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	   ResponseEntity<Book[]> reponse= restTemplate.exchange(url,HttpMethod.GET,entity, Book[].class,id);
	   if(reponse.getStatusCode()==HttpStatus.OK) { 
		   Book[] books=reponse.getBody();
		   readersDto.setBooks(Arrays.asList(books));
	   }
		return readersDto; 
	}

	@Override
	public List<ReadersDto> getAll() {
			List<Readers> readers=readersRepository.findAll();
		return readers.stream().map((reader)->convertToDto(reader)).collect(Collectors.toList()); 
	}
}
