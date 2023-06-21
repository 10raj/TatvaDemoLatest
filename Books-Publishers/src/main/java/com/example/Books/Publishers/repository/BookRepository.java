package com.example.Books.Publishers.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Books.Publishers.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByReaderId(Long readerId);

	@Query("select book.readerId from Book book where book.returnDate < now()")
	List<Long> findBookByReturnDate();

	@Query("from Book book where book.title=:bookName")
	Book findByTitle(@Param("bookName") String bookName);

}
