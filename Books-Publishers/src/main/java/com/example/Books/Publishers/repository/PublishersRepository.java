package com.example.Books.Publishers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Books.Publishers.entity.Publisher;

@Repository
public interface PublishersRepository extends JpaRepository<Publisher, Long>{

}
