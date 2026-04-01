package com.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entity.Book;

import jakarta.transaction.Transactional;

public interface BookRepo extends JpaRepository<Book, Integer> {

	public List<Book> findByNameContaining(String bookName);

	@Transactional
	@Modifying
	@Query("UPDATE Book b SET b.price = :price WHERE b.name =:name")
	int updatePriceByName(@Param("name") String name, @Param("price") double price);
}
