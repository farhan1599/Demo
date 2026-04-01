package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.dto.BookDto;
import com.demo.entities.BookEntity;
import com.demo.mapper.BookMapper;
import com.demo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepo;

	public BookServiceImpl(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}

	@Override
	public BookDto getBookById(Integer bookId) {
		Optional<BookEntity> byId = bookRepo.findById(bookId);
		if (byId.isPresent()) {
			BookEntity bookEntity = byId.get();
			BookDto bookDto = BookMapper.convertToDto(bookEntity);
			return bookDto;
		}
		return null;
	}

	@Override
	public List<BookDto> getBooksByName(String bookName) {
		List<BookDto> booksDto = new ArrayList<>();
		List<BookEntity> booksEntity = bookRepo.findByNameContaining(bookName);
		if (!booksEntity.isEmpty()) {
			for (BookEntity entity : booksEntity) {
				BookDto dto = BookMapper.convertToDto(entity);
				booksDto.add(dto);
			}
			return booksDto;
		}
		return null;
	}

}
