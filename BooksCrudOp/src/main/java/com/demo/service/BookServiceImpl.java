package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Book;
import com.demo.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo repo;

	@Override
	public String addBook(Book book) {
		Book saved = repo.save(book);
		if(saved.getId() != null) {
			return "Inserted";
		}
		return "Failed to insert";
	}

	@Override
	public Book getById(Integer id) {
		Optional<Book> byId = repo.findById(id);
		if (byId.isPresent()) {
			Book book = byId.get();
			return book;
		}
		return null;
	}
	
	@Override
	public List<Book> findBookByName(String bookName) {
		List<Book> bookByName = repo.findByNameContaining(bookName);
		return bookByName;
	}

	
	@Override
	public List<Book> getAllBook() {
		
		List<Book> books = repo.findAll();
		
		return books;
	}
	
	
	@Override
	public String changeBookPrice(String name, double price) {
		int updatePrice = repo.updatePriceByName(name, price);
		if(updatePrice==1) {
			return "Price updated successfuly";
		}
		return "Failed to update price";
	}
}
