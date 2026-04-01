package com.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AuthorDto;
import com.demo.response.ApiResponse;
import com.demo.service.AuthorService;
@CrossOrigin(origins = "*")
@RestController
public class AuthorRestController {

	private final AuthorService authorService;

	public AuthorRestController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@PostMapping("/save")
	public ResponseEntity<ApiResponse<AuthorDto>> addAuthor(@RequestBody AuthorDto authorDto) {
		ApiResponse<AuthorDto> response = new ApiResponse<>();
		Boolean status = authorService.saveAuthor(authorDto);
		if (status) {
			response.setStatus(201);
			response.setMsg("Saved successfully");
			response.setData(authorDto);
			return new ResponseEntity<ApiResponse<AuthorDto>>(response, HttpStatus.CREATED);
		} else {
			response.setStatus(400);
			response.setMsg("Failed to save");
			response.setData(null);
			return new ResponseEntity<ApiResponse<AuthorDto>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/id/{authorId}")
	public ResponseEntity<ApiResponse<AuthorDto>> getAuthorById(@PathVariable("authorId") Integer authorId) {
		ApiResponse<AuthorDto> response = new ApiResponse<>();
		AuthorDto authorDto = authorService.getAuthorById(authorId);
		if (authorDto != null) {
			response.setStatus(200);
			response.setMsg("Author fetched successfully");
			response.setData(authorDto);
			return new ResponseEntity<ApiResponse<AuthorDto>>(response, HttpStatus.OK);
		} else {
			response.setStatus(404);
			response.setMsg("No records found with id:" + authorId);
			response.setData(null);
			return new ResponseEntity<ApiResponse<AuthorDto>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<ApiResponse<List<AuthorDto>>> getAuthorByName(@PathVariable("name") String name) {
		ApiResponse<List<AuthorDto>> response = new ApiResponse<>();
		List<AuthorDto> authorsByName = authorService.getAuthorsByName(name);
		if (!authorsByName.isEmpty()) {
			response.setStatus(200);
			response.setMsg("Authors with similar names");
			response.setData(authorsByName);
			return new ResponseEntity<ApiResponse<List<AuthorDto>>>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMsg("No Author found for the given name :" + name);
			response.setData(null);
			return new ResponseEntity<ApiResponse<List<AuthorDto>>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get")
	public ResponseEntity<ApiResponse<List<AuthorDto>>> getAllAuthors() {
		ApiResponse<List<AuthorDto>> response = new ApiResponse<>();
		List<AuthorDto> allAuthors = authorService.getAllAuthors();
		if (!allAuthors.isEmpty()) {
			response.setStatus(200);
			response.setMsg("Fetched Authors Successfully");
			response.setData(allAuthors);
			return new ResponseEntity<ApiResponse<List<AuthorDto>>>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMsg("Failed to fetch authors");
			response.setData(null);
			return new ResponseEntity<ApiResponse<List<AuthorDto>>>(response, HttpStatus.NOT_FOUND);

		}
	}
}
