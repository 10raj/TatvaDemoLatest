package com.example.Books.Publishers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Books.Publishers.dtos.PublisherDto;
import com.example.Books.Publishers.dtos.PublisherResponseDto;
import com.example.Books.Publishers.entity.Book;
import com.example.Books.Publishers.entity.Publisher;
import com.example.Books.Publishers.repository.BookRepository;
import com.example.Books.Publishers.repository.PublishersRepository;

@Service
public class PublisherServiceImpl implements PublishersService{

	@Autowired
	private PublishersRepository publishersRepository;
	
	@Autowired
	private KafkaService kafkaService;
	
	@Autowired
	private BookRepository booksRepository;
	
	
	@Override
	public List<PublisherDto> getAll() {
		List<Publisher> publishers= publishersRepository.findAll();
		return publishers.stream().map((publisher)->convertToDto(publisher)).collect(Collectors.toList());
	}

	@Override
	public PublisherDto getBookById(Long id) {
		Publisher publisher = publishersRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Given Publisher id : "+id+" doesn't exist"));
		return convertToDto(publisher);
	}

	@Override
	@Transactional
	public PublisherDto save(PublisherDto publisherDto) {
		String kafkaMessage="";
		Publisher publisher= new Publisher();
		List<Book> books= new ArrayList<Book>();
		if(publisherDto.getId()==null || publisherDto.getId()==0) {
			BeanUtils.copyProperties(publisherDto, publisher);
			kafkaMessage="Publisher with id : "+ publisher.getId()+" Saved ";
		}
		else {
			Publisher publisherDb = publishersRepository.findById(publisherDto.getId())
					.orElseThrow(()-> new IllegalArgumentException("Given Publisher id : "+publisherDto.getId()+" doesn't exist"));
			BeanUtils.copyProperties(publisherDto, publisher);
			kafkaMessage="Publisher with id : "+ publisher.getId()+" Updated ";
		}
		
		kafkaService.sendMessageToKafka(kafkaMessage);
		publishersRepository.save(publisher);
		if(!publisherDto.getBooks().isEmpty()) {
		publisher.getBooks().forEach((book)->{
			book.setPublisher(publisher);
			books.add(booksRepository.save(book));
		});
		publisher.setBooks(books);
		}
		return convertToDto(publisher);
	}

	@Override
	public void deleteBookById(Long id) {
		Publisher publisherDb = publishersRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Given Publisher id : "+id+" doesn't exist"));
		publishersRepository.delete(publisherDb);
	}
	
	
	private PublisherDto convertToDto(Publisher publisher) {
		PublisherDto publisherDto =new PublisherDto();
		BeanUtils.copyProperties(publisher, publisherDto);
		return publisherDto;
	}

}
