package com.demo.service;

import java.util.List;

import com.demo.entity.Book;

public interface BookService {

	
	public String addBook(Book book);
	
	public Book getById(Integer id);
	
	public List<Book> getAllBook();
	
	public List<Book> findBookByName(String bookName);
	
	public String changeBookPrice(String name , double price);
	
	
}
