package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

	public List<BookEntity> findByNameContaining(String bookName);
	
}
