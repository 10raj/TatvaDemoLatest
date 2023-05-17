package com.example.Readers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Readers.entity.Readers;

@Repository
public interface ReadersRepository extends JpaRepository<Readers, Long> {

}
