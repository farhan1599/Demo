package com.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Book;
import com.demo.service.BookService;

@RestController
public class BookRestController {

	@Autowired
	private BookService service;

	@PostMapping(value = "/add", produces = "application/json")
	public ResponseEntity<String> addBook(@RequestBody Book book) {

		String status = service.addBook(book);

		if (status.equals("Inserted")) {
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/book/{id}", produces = "application/json")

	public ResponseEntity<Book> getBookById(@PathVariable("id") Integer id) {

		Book book = service.getById(id);

		if (book != null) {

			return new ResponseEntity<Book>(book, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBook() {

		List<Book> allBooks = service.getAllBook();

		if (allBooks != null) {
			return new ResponseEntity<List<Book>>(allBooks, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/name/{bookName}")
	public ResponseEntity<List<Book>> getBookByName(@PathVariable("bookName") String bookName) {

		List<Book> booksByName = service.findBookByName(bookName);

		if (!booksByName.isEmpty()) {
			return new ResponseEntity<List<Book>>(booksByName, HttpStatus.OK);
		}
		return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/update")
	public ResponseEntity<String> updatePrice(@RequestParam String name, @RequestParam double price) {

		String status = service.changeBookPrice(name, price);

		if (status.equals("Price updated successfuly")) {
			return new ResponseEntity<String>(status, HttpStatus.OK);
		}
		return new ResponseEntity<String>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
