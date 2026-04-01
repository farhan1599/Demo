package com.demo.service;

import java.util.List;

import com.demo.dto.BookDto;

public interface BookService {
	
	public BookDto getBookById(Integer bookId);
	
	public List<BookDto> getBooksByName(String bookName);
	

}
