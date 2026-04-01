package com.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.BookDto;
import com.demo.response.ApiResponse;
import com.demo.service.BookService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("book")
public class BookRestController {

	private final BookService bookService;

	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("id/{id}")
	public ResponseEntity<ApiResponse<BookDto>> getBookById(@PathVariable("id") Integer id) {
		ApiResponse<BookDto> response = new ApiResponse<>();
		BookDto bookById = bookService.getBookById(id);
		if (bookById != null) {
			response.setStatus(200);
			response.setMsg("Successfully fetched the book with id :" + id);
			response.setData(bookById);
			return new ResponseEntity<ApiResponse<BookDto>>(response, HttpStatus.OK);
		} else {
			response.setStatus(404);
			response.setMsg("No Record present with id:" + id);
			response.setData(null);
			return new ResponseEntity<ApiResponse<BookDto>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<ApiResponse<List<BookDto>>> getBookByName(@PathVariable("name") String name) {
		ApiResponse<List<BookDto>> response = new ApiResponse<>();
		List<BookDto> booksByName = bookService.getBooksByName(name);
		if (!booksByName.isEmpty() && booksByName != null) {
			response.setStatus(200);
			response.setMsg("Fetched books with similar name");
			response.setData(booksByName);
			return new ResponseEntity<ApiResponse<List<BookDto>>>(response, HttpStatus.OK);
		} else {
			response.setStatus(404);
			response.setMsg("No record found for given name:" + name);
			response.setData(null);
			return new ResponseEntity<ApiResponse<List<BookDto>>>(response, HttpStatus.NOT_FOUND);
		}
	}

}
