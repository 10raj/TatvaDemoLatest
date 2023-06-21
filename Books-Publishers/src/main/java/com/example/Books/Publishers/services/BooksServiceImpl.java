package com.example.Books.Publishers.services;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.KafkaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.Books.Publishers.configurations.kafka.KafkaPublisher;
import com.example.Books.Publishers.dtos.BookRequestDto;
import com.example.Books.Publishers.dtos.BookResponseDto;
import com.example.Books.Publishers.entity.Book;
import com.example.Books.Publishers.entity.Publisher;
import com.example.Books.Publishers.repository.BookRepository;
import com.example.Books.Publishers.repository.PublishersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BooksServiceImpl implements BooksService{
	
	@Autowired
	private BookRepository booksRepository;
	
	@Autowired
	private PublishersRepository publishersRepository;
	
	@Autowired
	private KafkaService kafkaService;
	
	@Override
	public List<BookResponseDto> getAll() {
		List<Book> books = booksRepository.findAll();
		return books.stream().map(book->convertToDto(book)).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "books", key = "#id")
	public BookResponseDto getBookById(Long id) {
		Book book = booksRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Given Book "+id+" doesn't exist"));
		return convertToDto(book);
	}

	@Override
	public BookResponseDto save(BookRequestDto bookRequestDto) {
		
		String kafkaMessage = "";
		Book book = new Book();
		if (bookRequestDto.getId() == null || bookRequestDto.getId() == 0) {
			if (bookRequestDto.getPublisherId() != null) {
				Publisher publisher = publishersRepository.findById(bookRequestDto.getPublisherId())
						.orElseThrow(() -> new IllegalArgumentException(
								"Given Publisher " + bookRequestDto.getPublisherId() + " doesn't exist"));
				book.setPublisher(publisher);
			}
			BeanUtils.copyProperties(bookRequestDto, book);
			kafkaMessage="book "+book.getTitle()+" saved successfully";
		} else {
			Book bookDb = booksRepository.findById(bookRequestDto.getId()).orElseThrow(
					() -> new IllegalArgumentException("Given Book " + bookRequestDto.getId() + " doesn't exist"));
			Publisher publisher = publishersRepository.findById(bookRequestDto.getPublisherId())
					.orElseThrow(() -> new IllegalArgumentException(
							"Given Publisher " + bookRequestDto.getPublisherId() + " doesn't exist"));
			book.setPublisher(publisher);
			BeanUtils.copyProperties(bookRequestDto, book);
			book.setId(bookDb.getId());
			kafkaMessage="book "+book.getTitle()+" updated successfully";

		}
		book = booksRepository.save(book);
		
		kafkaService.sendMessageToKafka(kafkaMessage);
		return convertToDto(book);
	}

	
	
	@Override
	@CacheEvict(value = "books", key = "#id")
	public void deleteBookById(Long id) {
		String kafkaMessage="";
		Book bookDb=booksRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Given Book "+id+" doesn't exist"));
		booksRepository.delete(bookDb);
		kafkaMessage="book "+ bookDb.getTitle() +"deleted succesfully";
		kafkaService.sendMessageToKafka(kafkaMessage);
	}
	
	private BookResponseDto convertToDto(Book book) {
		BookResponseDto bookDto=new BookResponseDto();
		BeanUtils.copyProperties(book, bookDto);
		return bookDto;
	}

	@Override
	public List<BookResponseDto> getBookByReaderId(Long readerId) {
		List<Book> books = booksRepository.findByReaderId(readerId);
		return books.stream().map((book)->convertToDto(book)).collect(Collectors.toList());
	}

	@Override
	public long[] bookByDueDate() {
		List<Long> readersList=booksRepository.findBookByReturnDate();
		log.info("dueDateMethod called:{}",readersList.toString());
		long[] result = readersList.stream().mapToLong(l -> l).toArray();
		return result;
	}

	@Override
	public BookResponseDto getBookByName(String bookName) {
		Book book= booksRepository.findByTitle(bookName);
		BookResponseDto bookDto= new BookResponseDto();
		BeanUtils.copyProperties(book, bookDto);
		return bookDto;
	}

}
