package com.demo.service;

import java.util.List;

import com.demo.dto.AuthorDto;

public interface AuthorService {
	
	public Boolean saveAuthor(AuthorDto authorDto);
	
	public AuthorDto getAuthorById(Integer authorId);
	
	public List<AuthorDto> getAuthorsByName(String authorName);
	
	public List<AuthorDto> getAllAuthors();

}
